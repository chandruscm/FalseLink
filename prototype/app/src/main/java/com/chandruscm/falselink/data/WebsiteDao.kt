package com.chandruscm.falselink.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chandruscm.falselink.data.Website.Status

@Dao
interface WebsiteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertWebsite(website: Website?): Long

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