package com.chandruscm.falselink.ui.verify

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.databinding.DialogVerifyBinding
import com.chandruscm.falselink.di.injector
import com.chandruscm.falselink.di.viewModel
import kotlinx.android.synthetic.main.dialog_verify.*
import timber.log.Timber

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
    }

    private fun subscribeUi() {
        viewModel.verificationResult.observe(this, Observer { result ->
            when (result) {
                is Result.Safe -> startBrowser()
                is Result.Dangerous -> showWarning()
                is Result.Error -> showError()
            }
        })
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

    private fun showWarning() {
        Timber.d("Expanding dialog")
        dialog_verify_detail.isVisible = true
    }

    private fun showError() {

    }
}