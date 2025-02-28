package com.luvio.api.api

import com.luvio.api.NetworkResult
import com.luvio.api.auth.EmailVerificationRequest
import com.luvio.api.auth.LoginRequest
import com.luvio.api.auth.RegistrationRequest
import com.luvio.api.auth.RegistrationResponse

interface AuthService {

    suspend fun register(body: RegistrationRequest): NetworkResult<RegistrationResponse>

    suspend fun verifyEmail(body: EmailVerificationRequest): NetworkResult<Unit>

    suspend fun login(body: LoginRequest): NetworkResult<Unit>
}