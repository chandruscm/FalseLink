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

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.databinding.DialogVerifyBinding
import com.chandruscm.falselink.di.injector
import com.chandruscm.falselink.di.viewModel
import com.chandruscm.falselink.utils.onClick
import com.chandruscm.falselink.utils.openWebsiteUri

/*
 * Shows the verify dialog when a URL is intercepted.
 */
class VerifyActivity : AppCompatActivity() {

    private val viewModel by viewModel {
        injector.verifyViewModelFactory.create(uri = intent.data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = DataBindingUtil.setContentView<DialogVerifyBinding>(
            this, R.layout.dialog_verify
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.proceedButton.onClick(::saveAndProceed)
        binding.blockButton.onClick(::block)

        subscibeUi()
    }

    /**
     * Observe for verification result, also observed by binding adapter.
     * TODO: Should all safe websites be added to the db?
     * TODO: How to handle verification errors? UX or UserSafety?
     */
    private fun subscibeUi() {
        viewModel.verificationResult.observe(this, Observer { result ->
            when (result) {
                is Result.Safe -> startBrowser(result.uri)
            }
        })
    }

    /**
     * Allows dangerous websites to be safelisted and opened.
     * Open browser after db update is complete.
     */
    private fun saveAndProceed() {
        viewModel.addWebsiteToSafeList().invokeOnCompletion {
            startBrowser()
        }
    }

    /**
     * Add dangerous website to blocked list.
     * Close dialog after db update is complete.
     */
    private fun block() {
        viewModel.addWebsiteToBlockedList().invokeOnCompletion {
            finish()
        }
    }

    /**
     * Pass the final url to the default browser.
     */
    private fun startBrowser(uri: Uri? = viewModel.getUri()) {
        openWebsiteUri(this, uri)
        finish()
    }
}