package com.chandruscm.falselink.utils

import android.net.Uri
import android.view.View

/**
 * Construct base URL string with protocol and host
 */
fun Uri.formatted(): String {
    return scheme + "://" + host
}

/**
 * an onClick function with an [action] that does not pass
 * the view that has been clicked (in contract to @see View.setOnClickListener)
 * This enables better usage of function references
 */
fun View.onClick(action: () -> Unit) {
    setOnClickListener { action() }
}
