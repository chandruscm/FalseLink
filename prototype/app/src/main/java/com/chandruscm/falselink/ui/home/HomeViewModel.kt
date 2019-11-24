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