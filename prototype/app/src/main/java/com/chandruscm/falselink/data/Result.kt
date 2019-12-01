package com.chandruscm.falselink.data

import android.net.Uri

/**
 * Verification result holder.
 * - Safe : Verified Safe (HTTPS & NORMAL)
 * - Dangerous : Verified Dangerous (!HTTP || !NORMAL)
 * - Error : Verification failed
 *
 * @param uri - Final redirected/expanded Uri
 * @param website - Website after verification
 * @param error - Error thrown during Network I/O
 */
sealed class Result<T> {
    class Safe<T>(val uri: Uri? = null) : Result<T>()
    class Dangerous<T>(val uri: Uri?, val website: Website) : Result<T>()
    class Error<T>(val error: Throwable? = null) : Result<T>()
}
