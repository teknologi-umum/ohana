package com.teknologiumum.plugins

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*

fun Application.configureHTTP() {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        allowCredentials = false
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

}
