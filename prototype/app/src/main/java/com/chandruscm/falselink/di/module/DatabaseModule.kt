package com.chandruscm.falselink.di.module

import android.content.Context
import com.chandruscm.falselink.data.WTLDatabase
import dagger.Module
import dagger.Provides

@Module
object DatabaseModule {

    @Provides
    @JvmStatic
    fun provideDb(context: Context) = WTLDatabase.getDatabase(context)

    @Provides
    @JvmStatic
    fun provideWebsiteDao(db: WTLDatabase) = db.websiteDao()
}