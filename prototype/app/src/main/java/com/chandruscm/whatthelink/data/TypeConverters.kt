package com.chandruscm.whatthelink.data

import androidx.room.TypeConverter
import com.chandruscm.whatthelink.data.Website.VerificationStatus.*
import com.chandruscm.whatthelink.data.Website.ContentType.*

class VerificationStatusConverter {

    @TypeConverter
    fun toVerificationStatus(value: Int): Website.VerificationStatus = when (value) {
        BLACK_LISTED.ordinal -> BLACK_LISTED
        WHITE_LISTED.ordinal -> WHITE_LISTED
        else -> Website.VerificationStatus.UNVERIFIED
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
        else -> Website.ContentType.UNVERIFIED
    }

    @TypeConverter
    fun toInteger(type: Website.ContentType): Int = type.ordinal
}
