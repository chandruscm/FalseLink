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

package com.chandruscm.falselink.managers

import android.content.SharedPreferences
import androidx.core.content.edit
import com.chandruscm.falselink.utils.PREF_VERIFICATION_ENABLE
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SharedPreferencesManager @Inject constructor(
    private val preferences: SharedPreferences
) {

    fun isVerificationEnabled() =
        preferences.getBoolean(PREF_VERIFICATION_ENABLE, true)

    fun setVerificationEnabled(enable: Boolean) {
        preferences.edit {
            putBoolean(PREF_VERIFICATION_ENABLE, enable)
        }
    }
}