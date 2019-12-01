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

import androidx.room.TypeConverter
import com.chandruscm.falselink.data.Website.Protocol.*
import com.chandruscm.falselink.data.Website.Status.*
import com.chandruscm.falselink.data.Website.ContentType.*

/**
 * Room TypeConverters for the enum fields.
 */
class WebsiteProtocolConverter {

    @TypeConverter
    fun toProtocol(value: Int): Website.Protocol = when (value) {
        HTTP.ordinal -> HTTP
        HTTPS.ordinal -> HTTPS
        BAD_CERT.ordinal -> BAD_CERT
        else -> throw Error("Invalid website protocol.")
    }

    @TypeConverter
    fun toInteger(protocol: Website.Protocol): Int = protocol.ordinal
}

class WebsiteStatusConverter {

    @TypeConverter
    fun toStatus(value: Int): Website.Status = when (value) {
        BLOCKED.ordinal -> BLOCKED
        SAFE.ordinal -> SAFE
        else -> throw Error("Invalid website status.")
    }

    @TypeConverter
    fun toInteger(status: Website.Status): Int = status.ordinal
}

class WebsiteContentTypeConverter {

    @TypeConverter
    fun toContentType(value: Int): Website.ContentType = when (value) {
        NORMAL.ordinal -> NORMAL
        ADULT.ordinal -> ADULT
        BETTING_GAMBLING.ordinal -> BETTING_GAMBLING
        VIOLENCE.ordinal -> VIOLENCE
        ADVERTISEMENT_SPAM.ordinal -> ADVERTISEMENT_SPAM
        PHISHING.ordinal -> PHISHING
        ILLEGAL.ordinal -> ILLEGAL
        else -> throw Error("Invalid website verification result.")
    }

    @TypeConverter
    fun toInteger(contentType: Website.ContentType): Int = contentType.ordinal
}
