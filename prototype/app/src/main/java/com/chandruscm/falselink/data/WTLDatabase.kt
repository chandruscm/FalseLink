package com.chandruscm.falselink.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chandruscm.falselink.utils.DATABASE_NAME

@Database(
    entities = [Website::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(value = [
    VerificationStatusConverter::class,
    ContentTypeConverter::class
])
abstract class WTLDatabase : RoomDatabase() {

    abstract fun websiteDao(): WebsiteDao

    companion object {

        @Volatile
        private var instance: WTLDatabase? = null

        fun getInstance(context: Context): WTLDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WTLDatabase {
            return Room.databaseBuilder(context, WTLDatabase::class.java, DATABASE_NAME)
                    .createFromAsset("seed_websites.db")
                    .build()
        }
    }
}