package com.chandruscm.whatthelink.di.module

import android.content.Context
import com.chandruscm.whatthelink.data.WTLDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    @JvmStatic
    fun provideDb(context: Context) = WTLDatabase.getInstance(context)

    @Provides
    @JvmStatic
    fun provideWebsiteDao(db: WTLDatabase) = db.websiteDao()
}