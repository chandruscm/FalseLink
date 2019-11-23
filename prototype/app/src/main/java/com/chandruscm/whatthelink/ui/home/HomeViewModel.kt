package com.chandruscm.whatthelink.ui.home

import androidx.lifecycle.ViewModel
import com.chandruscm.whatthelink.data.WebsiteDao
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val websiteDao: WebsiteDao
) : ViewModel() {

    fun getWebsites() = websiteDao.getAllWebsites()
}