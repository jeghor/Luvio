package com.luvio.api

import com.luvio.api.model.ApiError
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

suspend inline fun <reified T : Any> HttpResponse.handleApi(): NetworkResult<T> {
    return try {
        when (status) {
            HttpStatusCode.OK -> {
                val data = body<T>()
                NetworkResult.Success(data)
            }

            else -> {
                val errorBody = body<ApiError>()
                NetworkResult.Error(status, errorBody)
            }
        }
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}
