package com.chandruscm.falselink.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WebsiteDao {

    @Query("SELECT * FROM websites WHERE verificationStatus = 1")
    fun getSafeWebsites(): LiveData<List<Website>>

    @Query("SELECT * FROM websites WHERE verificationStatus = 2")
    fun getBlockedWebsites(): LiveData<List<Website>>

    @Query("SELECT * FROM websites WHERE url = :url")
    suspend fun getWebsite(url: String?): Website?

    @Insert
    suspend fun addWebsite(website: Website)
}