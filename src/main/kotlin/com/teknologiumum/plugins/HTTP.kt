package com.teknologiumum.plugins

import io.ktor.http.*
import io.ktor.features.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureHTTP() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        allowCredentials = false
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

}
