package com.teknologiumum.commons

import kotlinx.serialization.Serializable


@Serializable
class EndpointDTO(
    val name: String,
    val url: String,
    val description: String? = "",
    val timeout: Int? = 10,
    val interval: Int? = 30
)
