package com.chandruscm.whatthelink.ui.verify

import android.net.Uri
import android.os.Handler
import androidx.core.os.postDelayed
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject

class VerifyViewModel @AssistedInject constructor(
    @Assisted private val uri: Uri?
) : ViewModel() {

    @AssistedInject.Factory
    interface Factory {
        fun create(uri: Uri?): VerifyViewModel
    }

    private val handler = Handler()

    val verificationStatus = MutableLiveData(false)

    fun verifyLink() {
        handler.postDelayed(5000) {
            verificationStatus.postValue(true)
        }
    }

    fun getUri() = uri
}