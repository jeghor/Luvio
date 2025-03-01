package com.luvio.api

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode

suspend inline fun <reified T : Any> HttpResponse.handleApi(): NetworkResult<T> {
    return try {
        when (status) {
            HttpStatusCode.OK -> NetworkResult.Success(body())
            else -> NetworkResult.Error(body())
        }
    } catch (e: Throwable) {
        NetworkResult.Exception(e = e)
    }
}
