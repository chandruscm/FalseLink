package com.chandruscm.falselink.ui.website

import androidx.lifecycle.ViewModel
import com.chandruscm.falselink.data.WebsiteRepository
import com.chandruscm.falselink.utils.TAB_WEBSITE_FRAGMENT_SAFE
import javax.inject.Inject

class WebsiteViewModel @Inject constructor(
    private val repository: WebsiteRepository
) : ViewModel() {

    fun getWebsites(tabType: Int) = when (tabType) {
        TAB_WEBSITE_FRAGMENT_SAFE -> repository.getSafeWebsites()
        else -> repository.getBlockedWebsites()
    }
}