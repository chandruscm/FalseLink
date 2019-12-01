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