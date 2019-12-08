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

package com.chandruscm.falselink.data

import javax.inject.Inject
import com.chandruscm.falselink.data.Website.Status.SAFE
import com.chandruscm.falselink.data.Website.Status.BLOCKED

class WebsiteRepository @Inject constructor(
    private val websiteDao: WebsiteDao
) {

    suspend fun addWebsite(website: Website?) = websiteDao.insertOrUpdate(website)

    suspend fun removeWebsite(website: Website?) = websiteDao.removeWebsite(website)

    suspend fun moveWebsite(website: Website?) = websiteDao.updateWebsite(website?.apply {
        status = if (status == SAFE) BLOCKED else SAFE
    })

    suspend fun getWebsite(host: String?) = websiteDao.getWebsiteByHost(host)

    fun getSafeWebsites() = websiteDao.getWebsitesByStatus(SAFE)

    fun getBlockedWebsites() = websiteDao.getWebsitesByStatus(BLOCKED)
}