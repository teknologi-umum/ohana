package com.teknologiumum.commons

import com.google.gson.annotations.SerializedName

class ResponseDTO(
    @SerializedName("success") val success: Boolean,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("requestDuration") val requestDuration: Long,
    name: String,
    url: String,
    description: String?,
    timeout: Int?,
    interval: Int?
) : EndpointDTO(name, url, description, timeout, interval)
