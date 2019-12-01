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
