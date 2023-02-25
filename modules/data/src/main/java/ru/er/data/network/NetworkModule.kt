package ru.er.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import ru.er.core.http.Authorized

import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    fun provideJson(): Json  = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        coerceInputValues = true
        isLenient = true
    }


    @Provides
    @Singleton
    @Authorized
    fun provideAuthHttpClient(
        json: Json,
        @Authorized okhttp: OkHttpClient,
        exceptionsHandler: HttpExceptionsHandler
    ) = createHttpClient(okhttp, json, exceptionsHandler)

    @Provides
    @Singleton
    fun provideHttpClient(
        json: Json,
        okhttp: OkHttpClient,
        exceptionsHandler: HttpExceptionsHandler
    ) = createHttpClient(okhttp, json, exceptionsHandler)


    private fun createHttpClient(
        okhttp: OkHttpClient,
        json: Json,
        exceptionsHandler: HttpExceptionsHandler
    ) = HttpClient(OkHttp) {
        engine {
            preconfigured = okhttp
            config {
                retryOnConnectionFailure(true)
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
        install(HttpCallValidator) {
            handleResponseException(exceptionsHandler::handle)
        }
        install(HttpTimeout)
    }

    @Provides
    @Singleton
    @Authorized
    fun provideAuthorizedOkHttpClient(
        authorization: AuthorizationInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authorization)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

    @Provides
    @Singleton
    fun provideUnAuthorizedOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()


}