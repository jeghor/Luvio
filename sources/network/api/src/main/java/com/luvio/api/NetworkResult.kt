package com.luvio.api

import io.ktor.client.statement.HttpResponse

sealed interface NetworkResult<T : Any> {

    class Success<T : Any>(val data: T) : NetworkResult<T>
    class Error<T : Any>(val error: HttpResponse) : NetworkResult<T>
    class Exception<T : Any>(val e: Throwable) : NetworkResult<T>
}