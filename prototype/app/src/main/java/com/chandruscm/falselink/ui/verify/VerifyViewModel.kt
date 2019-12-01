package com.chandruscm.falselink.ui.verify

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.Status.SAFE
import com.chandruscm.falselink.data.Website.Status.BLOCKED
import com.chandruscm.falselink.data.Website.ContentType.*
import com.chandruscm.falselink.data.WebsiteRepository
import com.chandruscm.falselink.managers.VerificationManager
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class VerifyViewModel @AssistedInject constructor(
    @Assisted private val uri: Uri?,
    private val repository: WebsiteRepository,
    private val verificationManager: VerificationManager
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(uri: Uri?): VerifyViewModel
    }

    val verificationResult: LiveData<Result<Unit>>

    /**
     * Start verification as soon as url is obtained.
     */
    init {
        verificationResult = verificationManager.verify(
                uri = uri,
                scope = viewModelScope
            )
    }

    fun addWebsiteToSafeList() = viewModelScope.launch {
        with(verificationResult.value as Result.Dangerous) {
            repository.addWebsite(website.apply {
                status = SAFE
            })
        }
    }

    fun addWebsiteToBlockedList() = viewModelScope.launch {
        with(verificationResult.value as Result.Dangerous) {
            repository.addWebsite(website.apply {
                status = BLOCKED
            })
        }
    }

    /**
     * Return the original url if verification fails.
     */
    fun getUri() = uri
}