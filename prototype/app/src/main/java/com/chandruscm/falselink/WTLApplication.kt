package com.chandruscm.falselink

import android.app.Application
import com.chandruscm.falselink.di.DaggerApplicationComponent
import com.chandruscm.falselink.di.InjectorProvider
import timber.log.Timber

class WTLApplication : Application(), InjectorProvider {

    override val component by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        setupTimber()
    }

    fun setupTimber() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
