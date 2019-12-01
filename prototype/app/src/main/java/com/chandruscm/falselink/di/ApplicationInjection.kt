/*
 * Copyright 2019 Chandramohan Sudar
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
