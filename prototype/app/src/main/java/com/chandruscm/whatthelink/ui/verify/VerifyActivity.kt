package com.chandruscm.whatthelink.ui.verify

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.chandruscm.whatthelink.R
import com.chandruscm.whatthelink.di.injector
import com.chandruscm.whatthelink.di.viewModel
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
        setContentView(R.layout.dialog_verify)

        Timber.d("Intercepted uri ${viewModel.getUri()}")
        viewModel.verifyLink()
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.verificationStatus.observe(this, Observer { complete ->
            if (complete) startBrowser()
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
}