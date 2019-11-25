package com.chandruscm.falselink.data

/*
 * Link verification result holder.
 */
sealed class Result<T> {
    class Safe<T> : Result<T>()
    class Dangerous<T>(val reason: String? = null) : Result<T>()
    class Error<T>(val error: Throwable? = null) : Result<T>()
}