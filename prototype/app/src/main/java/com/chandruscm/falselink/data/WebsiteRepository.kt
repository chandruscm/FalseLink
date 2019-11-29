package com.chandruscm.falselink.data

import javax.inject.Inject
import com.chandruscm.falselink.data.Website.Status.SAFE
import com.chandruscm.falselink.data.Website.Status.BLOCKED

class WebsiteRepository @Inject constructor(
    private val websiteDao: WebsiteDao
) {

    suspend fun addWebsite(website: Website?) = websiteDao.insertOrUpdate(website)

    suspend fun getWebsite(host: String?) = websiteDao.getWebsiteByHost(host)

    fun getSafeWebsites() = websiteDao.getWebsitesByStatus(SAFE)

    fun getBlockedWebsites() = websiteDao.getWebsitesByStatus(BLOCKED)
}