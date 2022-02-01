package com.teknologiumum.commons

import com.google.gson.annotations.SerializedName

open class EndpointDTO(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    @SerializedName("description") val description: String? = "",
    @SerializedName("timeout") val timeout: Int? = 10,
    @SerializedName("interval") val interval: Int? = 30
    )
