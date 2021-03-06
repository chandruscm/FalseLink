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

package com.chandruscm.falselink.ui.home

import androidx.databinding.Bindable
import com.chandruscm.falselink.managers.SharedPreferencesManager
import com.chandruscm.falselink.utils.ObservableViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val preferencesManager: SharedPreferencesManager
) : ObservableViewModel() {

    /*
     * Two-way data binding for the switch button
     */
    var verificationEnabled: Boolean
        @Bindable get() {
            return preferencesManager.isVerificationEnabled()
        }
        set(enable) {
            preferencesManager.setVerificationEnabled(enable)
        }
}