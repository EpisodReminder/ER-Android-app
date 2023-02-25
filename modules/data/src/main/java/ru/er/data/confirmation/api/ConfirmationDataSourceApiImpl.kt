package ru.er.data.confirmation.api

import dagger.Reusable
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import ru.er.core.Config
import ru.er.data.confirmation.models.CheckConfirmationCodeBody
import ru.er.data.confirmation.models.ConfirmationCodeResponse
import javax.inject.Inject
import javax.inject.Singleton

@Reusable
class ConfirmationDataSourceApiImpl @Inject constructor(
    private val config: Config, private val client: HttpClient
) : ConfirmationDataSourceApi {

    override suspend fun checkConfirmationCode(code: String): Boolean =
        client.get<ConfirmationCodeResponse>("${config.baseApiUrl}/login/check-key") {
            contentType(ContentType.Application.Json)
            body = CheckConfirmationCodeBody(code)
        }.isCodeCorrect

}