package com.chandruscm.falselink.ui.verify

import android.content.Intent
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

    private fun subscibeUi() {
        viewModel.verificationResult.observe(this, Observer { result ->
            when (result) {
                is Result.Trusted -> startBrowser()
                is Result.Safe -> startBrowser()
                is Result.Error -> startBrowser()
            }
        })
    }

    private fun saveAndProceed() {
        viewModel.addWebsiteToSafeList().invokeOnCompletion {
            startBrowser()
        }
    }

    private fun block() {
        viewModel.addWebsiteToBlockedList().invokeOnCompletion {
            finish()
        }
    }

    private fun startBrowser() {
        val intent = Intent().apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            setAction(Intent.ACTION_VIEW)
            setData(viewModel.getUri())
            setPackage("com.android.chrome")
        }
        startActivity(intent)
        finish()
    }
}