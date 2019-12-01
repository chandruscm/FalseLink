package com.chandruscm.falselink.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "websites")
data class Website(
    val protocol: Protocol,
    @PrimaryKey
    val host: String,
    val name: String,
    var status: Status,
    val type: ContentType
) {

    /**
     * All hosting issues are considered BAD_CERT
     */
    enum class Protocol {
        HTTP,
        HTTPS,
        BAD_CERT
    }

    /**
     * Status of a website
     * - can be changed by the user.
     */
    enum class Status {
        SAFE,
        BLOCKED
    }

    /**
     * Result after verifying the website.
     */
    enum class ContentType {
        NORMAL,
        ADULT,
        BETTING_GAMBLING,
        VIOLENCE,
        ADVERTISEMENT_SPAM,
        PHISHING,
        ILLEGAL
    }

    fun isSafe() = status == Status.SAFE
}
