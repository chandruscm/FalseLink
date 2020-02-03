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

package com.chandruscm.falselink.jsoup

import android.os.Build
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class WebParseClient @Inject constructor() {

    suspend fun getDom(uri: String?) = withContext(Dispatchers.IO) {
        return@withContext Jsoup
            .connect(uri)
            .userAgent(buildUserAgentString())
            .timeout(10000)
            .get()
    }

    /**
     * User agent needs to be set inorder to act as proper browser.
     */
    private fun buildUserAgentString() = StringBuilder().apply {
        append("Mozilla/5.0")
        append(" (Linux; Android ").append(Build.VERSION.RELEASE).append(") ")
        append("AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.133 Mobile Safari/535.19")
    }.toString()
}