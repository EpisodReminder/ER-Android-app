package ru.er.data.network

import io.ktor.http.*
import okhttp3.Interceptor
import okhttp3.Response
import ru.er.domain.session.SessionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorizationInterceptor @Inject constructor(
    private val sessionRepository: SessionRepository,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(HttpHeaders.Authorization, sessionRepository.getAuthInfoConditionals())
            .build()
        return chain.proceed(request)
    }
}