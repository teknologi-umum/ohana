package com.teknologiumum.worker

import com.teknologiumum.commons.EndpointDTO
import com.teknologiumum.commons.ResponseDTO
import kotlinx.coroutines.future.await
import java.io.IOException
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.time.Duration

class Worker(private var endpoint: EndpointDTO) {
    suspend fun createRequest(): ResponseDTO {
        try {
            val client = HttpClient.newBuilder()
                .connectTimeout(
                    Duration.ofSeconds(
                        endpoint.timeout?.toLong() ?: 15
                    )
                )
                .build()
            val request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint.name))
                .GET()
                .timeout(Duration.ofSeconds(endpoint.timeout?.toLong() ?: 10))
                .build()

            val timeBeforeRequest: Long = System.currentTimeMillis()

            val response = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .await()

            val timeAfterRequest: Long = System.currentTimeMillis()

            return ResponseDTO(
                success = true,
                statusCode = response.statusCode(),
                requestDuration = timeAfterRequest - timeBeforeRequest,
                name = endpoint.name,
                url = endpoint.url,
                description = endpoint.description,
                interval = endpoint.interval,
                timeout = endpoint.timeout,
            )
        } catch (e: InterruptedException) {
            // TODO
            return ResponseDTO(
                success = false,
                statusCode = 0,
                requestDuration = 0,
                name = endpoint.name,
                url = endpoint.url,
                description = endpoint.description,
                interval = endpoint.interval,
                timeout = endpoint.timeout
            )
        } catch (e: IOException) {
            // TODO
            return ResponseDTO(
                success = false,
                statusCode = 0,
                requestDuration = 0,
                name = endpoint.name,
                url = endpoint.url,
                description = endpoint.description,
                interval = endpoint.interval,
                timeout = endpoint.timeout
            )
        }
    }
}
