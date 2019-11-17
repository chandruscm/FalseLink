package com.chandruscm.whatthelink.ui.verify

import android.net.Uri
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

    fun getUri() = uri
}