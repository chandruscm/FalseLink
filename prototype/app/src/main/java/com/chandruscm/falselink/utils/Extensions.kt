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

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.databinding.ViewDataBinding
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
 * Open the uri in the preferred browser.
 * TODO: Get the package name of the default browser.
 */
fun openWebsiteUri(context: Context, uri: Uri?) {
    val intent = Intent().apply {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        setAction(Intent.ACTION_VIEW)
        setData(uri)
        setPackage("com.android.chrome")
    }
    context.startActivity(intent)
}

/**
 * an onClick function with an [action] that does not pass
 * the view that has been clicked (in contract to @see View.setOnClickListener)
 * This enables better usage of function references
 */
fun View.onClick(action: () -> Unit) {
    setOnClickListener { action() }
}

/**
 * Reference iosched https://github.com/google/iosched
 * Compatibility removeIf since it was added in API 24
 */
fun <T> MutableCollection<T>.compatRemoveIf(predicate: (T) -> Boolean): Boolean {
    val it = iterator()
    var removed = false
    while (it.hasNext()) {
        if (predicate(it.next())) {
            it.remove()
            removed = true
        }
    }
    return removed
}
