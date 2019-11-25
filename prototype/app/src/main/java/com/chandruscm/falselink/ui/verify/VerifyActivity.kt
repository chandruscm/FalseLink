package com.chandruscm.falselink.ui.verify

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Result
import com.chandruscm.falselink.di.injector
import com.chandruscm.falselink.di.viewModel

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
        setContentView(R.layout.dialog_verify)

        subscribeUi()
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

    }

    private fun showError() {

    }
}