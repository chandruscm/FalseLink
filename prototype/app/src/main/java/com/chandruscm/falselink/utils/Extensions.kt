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

package com.chandruscm.falselink.utils

import android.net.Uri
import android.view.View
import org.jsoup.nodes.Document

/**
 * Construct base URL string with protocol and host
 */
fun Uri.formatted(): String {
    return scheme + "://" + host
}

/**
 * Get the baseUri from Jsoup Document
 */
fun Document.baseUriObject(): Uri? {
    return Uri.parse(baseUri())
}

/**
 * Get the host from Jsoup Document
 */
fun Document.host(): String {
    return Uri.parse(baseUri()).host ?: baseUri()
}

/**
 * Get the formatted host Uri from Jsoup Document
 */
fun Document.hostUri(): String? {
    return Uri.parse(baseUri()).formatted()
}

/**
 * an onClick function with an [action] that does not pass
 * the view that has been clicked (in contract to @see View.setOnClickListener)
 * This enables better usage of function references
 */
fun View.onClick(action: () -> Unit) {
    setOnClickListener { action() }
}
