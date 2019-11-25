package com.chandruscm.falselink.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "websites")
data class Website(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val url: String = "",
    val name: String = "N/A",
    val verificationStatus: VerificationStatus = VerificationStatus.UNASSIGNED,
    val contentType: ContentType = ContentType.UNVERIFIED
) {
    enum class VerificationStatus {
        UNASSIGNED,
        SAFE,
        BLOCKED
    }

    enum class ContentType {
        UNVERIFIED,
        NORMAL,
        ADULT,
        BETTING_GAMBLING,
        VIOLENCE,
        ADVERTISEMENT_SPAM,
        PHISHING,
        ILLEGAL,
        UNSECURE
    }

    fun isSafe() = verificationStatus == VerificationStatus.SAFE

    fun isBlocked() = verificationStatus == VerificationStatus.BLOCKED
}
