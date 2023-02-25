package ru.er.data.confirmation.models

import kotlinx.serialization.Serializable


@Serializable
data class ConfirmationCodeResponse(
    val isCodeCorrect: Boolean
)