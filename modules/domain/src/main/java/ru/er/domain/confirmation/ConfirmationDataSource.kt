package ru.er.domain.confirmation

interface ConfirmationDataSource {

    suspend fun checkConfirmationCode(code: String): Boolean

    fun loadDemoMode()
}