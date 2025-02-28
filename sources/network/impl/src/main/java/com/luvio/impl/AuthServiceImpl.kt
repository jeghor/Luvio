package com.luvio.impl

import com.luvio.api.*
import com.luvio.api.api.AuthService
import com.luvio.api.auth.*
import io.ktor.client.HttpClient
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AuthServiceImpl(
    private val client: HttpClient
) : AuthService {

    override suspend fun register(body: RegistrationRequest): NetworkResult<RegistrationResponse> =
        client.post {
            url(NetworkRoutes.Auth.REGISTER)
            contentType(ContentType.Application.Json)
            setBody(body)
        }
            .handleApi()

    override suspend fun verifyEmail(body: EmailVerificationRequest): NetworkResult<Unit> =
        client.post {
            url(NetworkRoutes.Auth.VERIFY_EMAIL)
            contentType(ContentType.Application.Json)
            setBody(body)
        }
            .handleApi()

    override suspend fun login(body: LoginRequest): NetworkResult<Unit> =
        client.post {
            url(NetworkRoutes.Auth.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(body)
        }
            .handleApi()
}