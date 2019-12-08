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

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chandruscm.falselink.data.Website.Status

@Dao
interface WebsiteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWebsite(website: Website?): Long

    @Delete
    suspend fun removeWebsite(website: Website?)

    @Update
    suspend fun updateWebsite(website: Website?)

    @Transaction
    suspend fun insertOrUpdate(website: Website?) {
        val result = insertWebsite(website)
        if (result == -1L) updateWebsite(website)
    }

    @Query("SELECT * FROM websites WHERE host = :host")
    suspend fun getWebsiteByHost(host: String?): Website?

    @Query("SELECT * FROM websites WHERE status = :status")
    fun getWebsitesByStatus(status: Status): LiveData<List<Website>>
}