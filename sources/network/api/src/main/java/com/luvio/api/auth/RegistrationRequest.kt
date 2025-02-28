package com.luvio.api.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequest(
    val phone: String,
    val email: String,
    val password: String
)