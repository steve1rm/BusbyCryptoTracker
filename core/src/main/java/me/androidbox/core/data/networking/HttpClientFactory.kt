package me.androidbox.core.data.networking

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import me.androidbox.core.BuildConfig

object HttpClientFactory {

    fun create(engine: HttpClientEngine): HttpClient {
        val client = HttpClient(engine) {
            install(Logging) {
                this.logger = Logger.ANDROID

                if(BuildConfig.DEBUG) {
                    this.level = LogLevel.ALL
                }
                else {
                    this.level = LogLevel.NONE
                }
            }

            install(ContentNegotiation) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        prettyPrint = true
                        coerceInputValues = true
                    }
                )
            }

            defaultRequest {
                contentType(ContentType.Application.Json)
            }
        }

        return client
    }
}