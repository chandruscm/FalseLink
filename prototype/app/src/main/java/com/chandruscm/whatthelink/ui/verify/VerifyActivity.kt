package com.chandruscm.whatthelink.ui.verify

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
        setContentView(R.layout.dialog_verify)

        Timber.d("Intercepted uri ${viewModel.getUri()}")
    }
}