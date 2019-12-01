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