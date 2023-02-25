package ru.er.data.network

import dagger.Reusable
import io.ktor.client.features.*
import io.ktor.client.statement.*
import ru.er.utils.HttpException
import javax.inject.Inject

@Reusable
class HttpExceptionsHandler @Inject constructor(
) {

    suspend fun handle(cause: Throwable) {
        if (cause is ResponseException) {
            handle(cause.response.status.value, cause.response.readText(), cause)
        } else {
            throw cause
        }
    }

    fun handle(code: Int, error: String, cause: Throwable? = null) {
        when (code) {
            401 -> {
                throw HttpException.Unauthorized(error, cause).also {
                    // sessionRepository.reset()
                }
            }
            in 400..499 -> {
                throw HttpException.Client(code, error, cause)
            }
            in 500..599 -> {
                val exception = HttpException.Server(code, error, cause)
                throw exception
            }
        }
    }
}
