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