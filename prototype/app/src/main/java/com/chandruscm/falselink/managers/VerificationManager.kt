package com.chandruscm.falselink.managers

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.Protocol.*
import com.chandruscm.falselink.data.Website.Status.*
import com.chandruscm.falselink.data.Website.ContentType.*
import com.chandruscm.falselink.data.WebsiteRepository
import com.chandruscm.falselink.jsoup.WebParseClient
import com.chandruscm.falselink.utils.baseUriObject
import com.chandruscm.falselink.utils.host
import com.chandruscm.falselink.utils.hostUri
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
    private val webParser: WebParseClient
) {

    private var title: String = "Website"
    private lateinit var dom: Document

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
                dom = webParser.getDom(uri?.toString())
                title = webParser.getDom(dom.hostUri()).title()
            } catch (exception: IOException) {
                verificationResult.postValue(Result.Error(exception))
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
             * 4.Use secret-sauce.
             * TODO: Provide error messages to user.
             * TODO: Plug-in tensorflow models.
             */
            Timber.d("Using secret sauce.")
            verificationResult.postValue(Result.Dangerous(
                    uri = dom.baseUriObject(),
                    website = Website(
                        protocol = HTTP,
                        host = dom.host(),
                        name = title,
                        status = BLOCKED,
                        type = ADVERTISEMENT_SPAM
                    )
                )
            )
        }
        return verificationResult
    }
}