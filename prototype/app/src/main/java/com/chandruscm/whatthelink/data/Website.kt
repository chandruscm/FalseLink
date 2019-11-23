package com.chandruscm.whatthelink.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "websites")
data class Website(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String = "",
    val name: String = "N/A",
    val verificationStatus: VerificationStatus = VerificationStatus.UNVERIFIED,
    val contentType: ContentType = ContentType.UNVERIFIED
) {
    enum class VerificationStatus {
        UNVERIFIED,
        WHITE_LISTED,
        BLACK_LISTED
    }

    enum class ContentType {
        UNVERIFIED,
        NORMAL,
        ADULT,
        BETTING_GAMBLING,
        VIOLENCE,
        ADVERTISEMENT_SPAM,
        PHISHING,
        ILLEGAL
    }
}