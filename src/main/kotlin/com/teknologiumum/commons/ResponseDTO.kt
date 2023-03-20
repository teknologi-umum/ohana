package com.teknologiumum.commons

import kotlinx.serialization.Serializable


@Serializable
class ResponseDTO(
    val success: Boolean,
    val statusCode: Int,
    val requestDuration: Long
)
