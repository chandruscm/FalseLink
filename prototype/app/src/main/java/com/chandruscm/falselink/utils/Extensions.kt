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
