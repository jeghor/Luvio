package com.luvio.api.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val id: Long,
    val email: String
)