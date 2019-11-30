package com.chandruscm.falselink.data

/**
 * Verification result holder.
 * - Trusted : Website found in db safelist
 * - Safe : Verified Safe (HTTPS & NORMAL)
 * - Dangerous : Verified Dangerous (!HTTP || !NORMAL)
 * - Error : Verification failed
 */
sealed class Result<T> {
    class Trusted<T> : Result<T>()
    class Safe<T>(val website: Website) : Result<T>()
    class Dangerous<T>(val website: Website) : Result<T>()
    class Error<T>(val error: Throwable? = null) : Result<T>()
}
