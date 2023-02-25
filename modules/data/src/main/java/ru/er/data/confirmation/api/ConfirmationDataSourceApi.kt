package ru.er.data.confirmation.api

interface ConfirmationDataSourceApi {

    suspend fun checkConfirmationCode(code: String): Boolean

}