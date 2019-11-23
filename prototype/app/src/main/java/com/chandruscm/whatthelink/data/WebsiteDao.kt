package com.chandruscm.whatthelink.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WebsiteDao {

    @Query("SELECT * FROM websites WHERE verificationStatus = 1")
    fun getWhiteListedWebsites(): LiveData<List<Website>>

    @Query("SELECT * FROM websites WHERE verificationStatus = 2")
    fun getBlackListedWebsites(): LiveData<List<Website>>

    @Query("SELECT * FROM websites")
    fun getAllWebsites(): LiveData<List<Website>>

    @Insert
    suspend fun addWebsite(website: Website)
}