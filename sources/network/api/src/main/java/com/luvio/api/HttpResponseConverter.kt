package com.luvio.api

import io.ktor.client.call.body
import io.ktor.client.plugins.*
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T : Any> HttpResponse.handleApi(): NetworkResult<T> {
    return try {
        NetworkResult.Success(body())
    } catch (e: RedirectResponseException) {
        // - 3xx status code
        NetworkResult.Error(error = e)
    } catch (e: ClientRequestException) {
        // - 4xx status code
        NetworkResult.Error(error = e)
    } catch (e: ServerResponseException) {
        // - 5xx status code
        NetworkResult.Error(error = e)
    } catch (e: Throwable) {
        NetworkResult.Exception(e = e)
    }
}
