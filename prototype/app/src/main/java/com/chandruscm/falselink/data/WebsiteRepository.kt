package com.chandruscm.falselink.data

import javax.inject.Inject

class WebsiteRepository @Inject constructor(
    private val websiteDao: WebsiteDao
) {

    suspend fun getWebsite(url: String?) = websiteDao.getWebsite(url)

    fun getSafeWebsites() = websiteDao.getSafeWebsites()

    fun getBlockedWebsites() = websiteDao.getBlockedWebsites()
}