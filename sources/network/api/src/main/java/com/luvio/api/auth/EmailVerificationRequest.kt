package com.luvio.api.auth

import kotlinx.serialization.Serializable

@Serializable
data class EmailVerificationRequest(
    val email: String,
    val code: String
)
