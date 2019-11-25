package com.chandruscm.falselink.utils

import android.net.Uri

/*
 * Construct base URL string with protocol and host
 */
fun Uri.formatted(): String {
    return scheme + "://" + host
}
