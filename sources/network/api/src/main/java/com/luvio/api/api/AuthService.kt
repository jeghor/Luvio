package com.luvio.api.api

import com.luvio.api.NetworkResult
import com.luvio.api.model.auth.EmailVerificationRequest
import com.luvio.api.model.auth.LoginRequest
import com.luvio.api.model.auth.RegistrationRequest
import com.luvio.api.model.auth.RegistrationResponse

interface AuthService {

    suspend fun register(body: RegistrationRequest): NetworkResult<RegistrationResponse>

    suspend fun verifyEmail(body: EmailVerificationRequest): NetworkResult<Unit>

    suspend fun login(body: LoginRequest): NetworkResult<Unit>
}