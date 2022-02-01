package com.teknologiumum

import com.google.gson.Gson
import com.teknologiumum.commons.EndpointDTO
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.teknologiumum.plugins.*
import com.teknologiumum.worker.Worker
import java.io.File
import kotlinx.coroutines.*

fun main(): Unit = runBlocking{
    val jsonString: String = File("../../../../config.json")
        .readText(Charsets.UTF_8)
    val gson = Gson()
    val endpoints: Array<EndpointDTO> = gson
        .fromJson(jsonString, Array<EndpointDTO>::class.java)

    val workers: ArrayList<Job> = ArrayList()
    endpoints.forEach { endpoint ->
        val worker = launch {
            Worker(endpoint)
        }
        worker.start()
        workers += worker
    }
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureHTTP()
        configureSerialization()
    }.start(wait = true)

    // FIXME: I don't know how do you wait for a shutdown call
    // (or presumably a SIGINT or SIGKILL system calls), but we should do this:
    workers.forEach(Job::cancel)
}

