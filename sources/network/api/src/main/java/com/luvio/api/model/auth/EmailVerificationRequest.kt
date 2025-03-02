package com.luvio.api.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class EmailVerificationRequest(
    val email: String,
    val code: String
)
