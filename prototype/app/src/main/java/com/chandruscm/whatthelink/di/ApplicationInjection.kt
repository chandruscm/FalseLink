package com.chandruscm.whatthelink.di

import android.content.Context
import com.chandruscm.whatthelink.di.module.DatabaseModule
import com.chandruscm.whatthelink.di.module.SharedPreferencesModule
import com.chandruscm.whatthelink.ui.home.HomeViewModel
import com.chandruscm.whatthelink.ui.verify.VerifyViewModel
import com.chandruscm.whatthelink.ui.website.WebsiteViewModel
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
