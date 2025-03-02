package com.luvio.api.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponse(
    val id: Long,
    val email: String
)