package com.chandruscm.whatthelink.data

sealed class Result<out T> {
    class loading<T> : Result<T>()
    data class error<T>(val message: String) : Result<T>()
    data class success<T>(val data: T) : Result<T>()
}