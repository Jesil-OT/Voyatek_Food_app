package com.assessment.voyatek.core.data.networking

import com.assessment.voyatek.core.domain.util.NetworkError
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import com.assessment.voyatek.core.domain.util.Result

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when (response.status.value) {
        in 200..299 -> {
            try {
                val data = response.body<T>()
                return Result.Success(data)
            } catch (e: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION_ERROR)
            }
        }

        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        429 -> Result.Error(NetworkError.TOO_MANY_REQUESTS)
        in 500..599 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> Result.Error(NetworkError.UNKNOWN_ERROR)
    }
}