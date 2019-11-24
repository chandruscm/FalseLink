package com.chandruscm.falselink.di.module

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides

@Module
object SharedPreferencesModule {

    @Provides
    @JvmStatic
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("default", Context.MODE_PRIVATE)
    }
}