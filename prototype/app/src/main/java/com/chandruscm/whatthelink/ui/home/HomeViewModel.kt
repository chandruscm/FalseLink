package com.chandruscm.whatthelink.ui.home

import androidx.lifecycle.ViewModel
import com.chandruscm.whatthelink.data.WebsiteDao
import com.chandruscm.whatthelink.utils.TAB_WEBSITE_FRAGMENT_WHITE_LIST
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val websiteDao: WebsiteDao
) : ViewModel() {

    fun getWebsites(tabType: Int) = when (tabType) {
        TAB_WEBSITE_FRAGMENT_WHITE_LIST -> websiteDao.getWhiteListedWebsites()
        else -> websiteDao.getBlackListedWebsites()
    }
}