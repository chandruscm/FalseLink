/*
 * Copyright 2019 Chandramohan Sudar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chandruscm.falselink.managers

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.Status.*
import com.chandruscm.falselink.data.Website.ContentType.*
import com.chandruscm.falselink.data.WebsiteRepository
import com.chandruscm.falselink.jsoup.WebParseClient
import com.chandruscm.falselink.tf.UrlClassificationClient
import com.chandruscm.falselink.utils.baseUriObject
import com.chandruscm.falselink.utils.host
import com.chandruscm.falselink.utils.hostUri
import com.chandruscm.falselink.utils.protocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jsoup.nodes.Document
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

/**
 * Single source of truth for verification.
 */
class VerificationManager @Inject constructor(
    private val preferencesManager: SharedPreferencesManager,
    private val repository: WebsiteRepository,
    private val webParser: WebParseClient,
    private val urlClassificationClient: UrlClassificationClient
) {

    private var title: String = "Website"
    private lateinit var dom: Document

    private var finalUrl: String = ""
    private var featureInput = arrayOf(floatArrayOf(0f, 0f, 0f, 0f, 0f))

    private val verificationResult = MutableLiveData<Result<Unit>>()

    fun isVerificationEnabled() =
        preferencesManager.isVerificationEnabled()

    /**
     * Expose the immutable verificationResult to the observer.
     * Run coroutine using viewModelScope
     */
    fun verify(uri: Uri?, scope: CoroutineScope): LiveData<Result<Unit>> {
        scope.launch {

            /**
             * 1.Check if user has enabled active verification.
             * - consider every website as Trusted if disabled.
             */
            if (!isVerificationEnabled()) {
                verificationResult.postValue(Result.Safe(uri))
                Timber.d("Verification not enabled, skipping verification.")
                return@launch
            }
            /**
             * 2.Get the dom with the final URL.
             */
            try {
                Timber.d("Attempting to parse URL ${uri?.toString()}")
                dom = webParser.getDom(uri?.toString())
                title = webParser.getDom(dom.hostUri()).title()
                finalUrl = dom.baseUriObject().toString()
                Timber.d("Final redirected URL is $finalUrl")

            } catch (exception: IOException) {
                Timber.d("Failed to parse URL due")
                verificationResult.postValue(Result.Error(exception))
                exception.printStackTrace()
                return@launch
            }
            /**
             * 3.Check if host is already verified.
             */
            repository.getWebsite(dom.host())?.let { website ->
                if (website.isSafe()) {
                    verificationResult.postValue(Result.Safe(dom.baseUriObject()))
                } else {
                    verificationResult.postValue(Result.Dangerous(
                            uri = dom.baseUriObject(),
                            website = website
                        )
                    )
                }
                Timber.d("Website found in db, skipping verification.")
                return@launch
            }

            /**
            * 4. Run the TensorFlowLite model
            */
            Timber.d("Running the TensorFlowLite model")

            /*
             * Feature 1: Length of URL
             */
            val urlLength = finalUrl.length
            featureInput[0][0] = when {
                urlLength < 54 -> 0f
                urlLength in 54..75 -> 2f
                else -> 1f
            }
            Timber.d("Feature 1: Uri Length $urlLength Input ${featureInput[0][0]}")

            /*
             * Feature 2: Contains "@" symbol
             */
            featureInput[0][1] = if (finalUrl.contains("@")) 1f else 0f
            Timber.d("Feature 2: @ symbol Input ${featureInput[0][1]}")

            /*
             * Feature 3: Contains "//"
             */
            featureInput[0][2] = if (finalUrl.contains("//")) 1f else 0f
            Timber.d("Feature 3: // symbol Input ${featureInput[0][2]}")

            /*
             * Feature 4: Contains "-" symbol
             */
            featureInput[0][3] = if (finalUrl.contains("-")) 1f else 0f
            Timber.d("Feature 4: - symbol Input ${featureInput[0][3]}")

            /*
             * Feature 5: Contains more than one sub-domain
             */
            val dotCount = finalUrl.count { it.equals(".") }
            featureInput[0][4] = when {
                dotCount < 3 -> 0f
                dotCount == 3 -> 2f
                else -> 1f
            }
            Timber.d("Feature 5: . symbol count ${featureInput[0][4]}")

            urlClassificationClient.modelInference(featureInput).let { confidence ->
                verificationResult.postValue(
                    if (confidence > 0.51f) {
                        Result.Safe(
                            uri = dom.baseUriObject()
                        )
                    } else {
                        Result.Dangerous(
                            uri = dom.baseUriObject(),
                            website = Website(
                                protocol = dom.protocol(),
                                host = dom.host(),
                                name = title,
                                status = BLOCKED,
                                type = PHISHING
                            )
                        )
                    }
                )
            }
        }
        return verificationResult
    }
}