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
    WebsiteProtocolConverter::class,
    WebsiteStatusConverter::class,
    WebsiteContentTypeConverter::class
])
abstract class WTLDatabase : RoomDatabase() {

    abstract fun websiteDao(): WebsiteDao

    companion object {
        /**
         * Singleton prevents multiple instances of database opening at the
         * same time.
         */
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
                ).createFromAsset("db/seed_websites.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}