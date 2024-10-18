package me.androidbox.core.data.networking

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import me.androidbox.core.domain.util.NetworkError
import me.androidbox.core.domain.util.Result

suspend inline fun <reified T> responseToResult(response: HttpResponse): Result<T, NetworkError> {
    /** Catches errors after making a request to the server */
    return when(response.status.value) {
        in 200..299 -> {
            try {
                /** Using suspend as parsing can take time */
                Result.Success(response.body<T>())
            }
            catch(ex: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> {
            Result.Error(NetworkError.REQUEST_TIMEOUT)
        }
        429 -> {
            Result.Error(NetworkError.TOO_MANY_REQUESTS)
        }
        in 500..599 -> {
            Result.Error(NetworkError.SERVER_ERROR)
        }
        else -> {
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}