package com.luvio.api.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val message: List<String>,
    val error: String,
    val statusCode: Int
)
