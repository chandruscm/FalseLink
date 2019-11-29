package com.chandruscm.falselink.managers

import android.net.Uri
import android.os.Handler
import androidx.core.os.postDelayed
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.Status.*
import com.chandruscm.falselink.data.Website.Protocol.*
import com.chandruscm.falselink.data.Website.ContentType.*
import com.chandruscm.falselink.data.WebsiteRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class VerificationManager @Inject constructor(
    private val preferencesManager: SharedPreferencesManager,
    private val repository: WebsiteRepository
) {

    private val handler = Handler()
    private val verificationResult = MutableLiveData<Result<Unit>>()

    fun isVerificationEnabled() =
        preferencesManager.isVerificationEnabled()

    /**
     * Expose the immutable verificationResult to the observer.
     */
    fun verify(uri: Uri?, scope: CoroutineScope): LiveData<Result<Unit>> {
        scope.launch {
            /**
             * 1.Check if user has enabled active verification.
             * - consider every website as Trusted if disabled.
             */
            if (!isVerificationEnabled()) {
                verificationResult.postValue(Result.Trusted())
                Timber.d("Verification not enabled, skipping verification.")
                return@launch
            }
            /**
             * 2.Check if website has been already verified.
             */
            repository.getWebsite(uri?.host)?.let { website ->
                verificationResult.postValue(Result.Trusted())
                Timber.d("Website found in safe list, skipping verification.")
                return@launch
            }
            /**
             * 3.Use secret-sauce.
             */
            Timber.d("Using secret sauce.")
            handler.postDelayed(5000) {
                verificationResult.postValue(Result.Dangerous(
                    Website(
                        protocol = HTTP,
                        host = "test-website.com",
                        name = "Test",
                        status = BLOCKED,
                        type = PHISHING
                    )
                ))
            }
        }
        return verificationResult
    }
}