package com.teknologiumum

import com.teknologiumum.commons.EndpointDTO
import com.teknologiumum.plugins.*
import com.teknologiumum.worker.Worker
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.*
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.io.File

fun main(): Unit = runBlocking {
    val jsonString: String = File("../../../../config.json")
        .readText(Charsets.UTF_8)
    val endpoints: Array<EndpointDTO> = Json
        .decodeFromString<Array<EndpointDTO>>(jsonString)

    val workers: ArrayList<Job> = ArrayList()
    endpoints.forEach { endpoint ->
        val worker = launch {
            // Should we put a try catch here?
            Worker(endpoint).start()
        }
        worker.start()
        workers += worker
    }

    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)

    // FIXME: I don't know how do you wait for a shutdown call
    // (or presumably a SIGINT or SIGKILL system calls), but we should do this:
    workers.forEach(Job::cancel)
}

fun Application.module() {
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureDatabases()
    configureRouting()
}

