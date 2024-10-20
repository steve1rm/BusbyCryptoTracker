package me.androidbox.core.data.networking

import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import me.androidbox.core.domain.util.NetworkError
import me.androidbox.core.domain.util.Result
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T>safeCall(request: () -> HttpResponse): Result<T, NetworkError> {
    val response = try {
        request()
    }
    /** Catches errors before making a request to the server */
    catch(ex: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET)
    }
    catch (ex: SerializationException) {
        return Result.Error(NetworkError.NO_INTERNET)
    }
    catch (ex: Exception) {
        /** Rethrow cancellation to cancel the parent scope can be notified
         *  that will cancel the scope */
        coroutineContext.ensureActive()

        ex.printStackTrace()
        return Result.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}