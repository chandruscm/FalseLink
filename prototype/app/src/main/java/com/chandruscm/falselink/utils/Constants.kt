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

package com.chandruscm.falselink.utils

import android.content.res.Resources
import com.chandruscm.falselink.R
import com.chandruscm.falselink.data.Website
import com.chandruscm.falselink.data.Website.Protocol.*
import com.chandruscm.falselink.data.Website.ContentType.*

/**
 * Constants used throughout the app.
 */
const val DATABASE_NAME = "whatthelink-db"

/**
 * Home tab fragment type.
 */
const val TAB_WEBSITE_FRAGMENT_TYPE = "FRAGMENT_TYPE"
const val TAB_WEBSITE_FRAGMENT_SAFE = 0
const val TAB_WEBSITE_FRAGMENT_BLOCKED = 1
const val COUNT_WEBSITE_FRAGMENTS = 2

/**
 * SharedPreferences keys
 */
const val PREF_VERIFICATION_ENABLE = "PREF_VERIFICATION_ENABLE"

/**
 * Get user messages for dangerous websites.
 */
fun getProtocolMessage(resources: Resources, protocol: Website.Protocol): String {
    return when (protocol) {
        HTTP -> resources.getString(R.string.protocol_http)
        else -> resources.getString(R.string.protocol_cert_expired)
    }
}

fun getContentTypeMessage(resources: Resources, contentType: Website.ContentType): String {
    return when (contentType) {
        ADULT -> resources.getString(R.string.content_type_adult)
        ADVERTISEMENT_SPAM -> resources.getString(R.string.content_type_advertisement)
        BETTING_GAMBLING -> resources.getString(R.string.content_type_betting_gambling)
        ILLEGAL -> resources.getString(R.string.content_type_illegal)
        PHISHING -> resources.getString(R.string.content_type_phishing)
        else -> resources.getString(R.string.content_type_violence)
    }
}
