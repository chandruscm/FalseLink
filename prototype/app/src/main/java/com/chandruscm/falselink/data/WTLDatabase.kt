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
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: WTLDatabase? = null

        fun getDatabase(context: Context): WTLDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WTLDatabase::class.java,
                    DATABASE_NAME
                ).createFromAsset("seed_websites.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}