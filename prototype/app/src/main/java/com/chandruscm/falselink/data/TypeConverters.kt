package com.chandruscm.falselink.data

import androidx.room.TypeConverter
import com.chandruscm.falselink.data.Website.VerificationStatus.*
import com.chandruscm.falselink.data.Website.ContentType.*

class VerificationStatusConverter {

    @TypeConverter
    fun toVerificationStatus(value: Int): Website.VerificationStatus = when (value) {
        BLOCKED.ordinal -> BLOCKED
        SAFE.ordinal -> SAFE
        else -> UNASSIGNED
    }

    @TypeConverter
    fun toInteger(status: Website.VerificationStatus): Int = status.ordinal
}

class ContentTypeConverter {

    @TypeConverter
    fun toContentType(value: Int): Website.ContentType = when (value) {
        NORMAL.ordinal -> NORMAL
        ADULT.ordinal -> ADULT
        BETTING_GAMBLING.ordinal -> BETTING_GAMBLING
        VIOLENCE.ordinal -> VIOLENCE
        ADVERTISEMENT_SPAM.ordinal -> ADVERTISEMENT_SPAM
        PHISHING.ordinal -> PHISHING
        ILLEGAL.ordinal -> ILLEGAL
        UNSECURE.ordinal -> UNSECURE
        else -> UNVERIFIED
    }

    @TypeConverter
    fun toInteger(type: Website.ContentType): Int = type.ordinal
}
