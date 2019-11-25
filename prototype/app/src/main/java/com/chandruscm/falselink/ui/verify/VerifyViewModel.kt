package com.chandruscm.falselink.ui.verify

import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import androidx.core.os.postDelayed
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.data.WebsiteRepository
import com.chandruscm.falselink.managers.SharedPreferencesManager
import com.chandruscm.falselink.utils.formatted
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

class VerifyViewModel @AssistedInject constructor(
    @Assisted private val uri: Uri?,
    private val repository: WebsiteRepository,
    private val preferencesManager: SharedPreferencesManager
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(uri: Uri?): VerifyViewModel
    }

    private val handler = Handler()
    val verificationResult = MutableLiveData<Result<Unit>>()

    init {
        verifyLink()
    }

    fun isVerificationEnabled() =
        preferencesManager.isVerificationEnabled()

    fun verifyLink() = viewModelScope.launch {
        /*
        * 1.Check if user has enabled active verification.
        */
        if (!isVerificationEnabled()) {
            verificationResult.postValue(Result.Safe())
            return@launch
        }
        /*
        * 2.Check if website has been already verified.
        */
        repository.getWebsite(uri?.formatted())?.let { website ->
            if (website.isSafe()) {
                verificationResult.postValue(Result.Safe())
                return@launch
            } else if (website.isBlocked()) {
                verificationResult.postValue(Result.Dangerous())
                return@launch
            }
        }
        /*
         * 3.Use secret-sauce.
         */
        handler.postDelayed(5000) {
            verificationResult.postValue(Result.Dangerous(TextUtils.join(
                "\n• ", arrayOf("uses unsecure HTTP", "contains advertising spams")
            )))
        }
    }

    fun getUri() = uri
}