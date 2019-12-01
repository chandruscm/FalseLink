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
fun getProtocolMessage(resources: Resources, protocol: Website.Protocol): String? {
    return when (protocol) {
        HTTP -> resources.getString(R.string.protocol_http)
        BAD_CERT -> resources.getString(R.string.protocol_cert_expired)
        else -> null
    }
}

fun getContentTypeMessage(resources: Resources, contentType: Website.ContentType): String? {
    return when (contentType) {
        ADULT -> resources.getString(R.string.content_type_adult)
        ADVERTISEMENT_SPAM -> resources.getString(R.string.content_type_advertisement)
        BETTING_GAMBLING -> resources.getString(R.string.content_type_betting_gambling)
        ILLEGAL -> resources.getString(R.string.content_type_illegal)
        PHISHING -> resources.getString(R.string.content_type_phishing)
        VIOLENCE -> resources.getString(R.string.content_type_violence)
        else -> null
    }
}
