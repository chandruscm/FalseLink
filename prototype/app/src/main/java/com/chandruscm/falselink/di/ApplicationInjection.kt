package com.chandruscm.falselink.di

import android.content.Context
import com.chandruscm.falselink.di.module.DatabaseModule
import com.chandruscm.falselink.di.module.SharedPreferencesModule
import com.chandruscm.falselink.ui.home.HomeViewModel
import com.chandruscm.falselink.ui.verify.VerifyViewModel
import com.chandruscm.falselink.ui.website.WebsiteViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AssistedInjectModule::class,
    SharedPreferencesModule::class,
    DatabaseModule::class
])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context) : ApplicationComponent
    }

    val homeViewModel: HomeViewModel
    val websiteViewModel: WebsiteViewModel
    val verifyViewModelFactory: VerifyViewModel.Factory
}

@AssistedModule
@Module(includes = [AssistedInject_AssistedInjectModule::class])
interface AssistedInjectModule
