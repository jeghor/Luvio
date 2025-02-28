package com.luvio.api

sealed interface NetworkResult<T : Any> {

    class Success<T : Any>(val data: T) : NetworkResult<T>
    class Error<T : Any>(val error: Throwable) : NetworkResult<T>
    class Exception<T : Any>(val e: Throwable) : NetworkResult<T>
}