package com.luvio.api

import com.luvio.api.model.ApiError
import io.ktor.http.HttpStatusCode

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val statusCode: HttpStatusCode, val error: ApiError) : NetworkResult<Nothing>()
    data class Exception(val exception: Throwable) : NetworkResult<Nothing>()
}