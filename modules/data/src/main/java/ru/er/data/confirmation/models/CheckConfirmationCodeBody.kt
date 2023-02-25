package ru.er.data.confirmation.models

import kotlinx.serialization.Serializable

@Serializable
data class CheckConfirmationCodeBody(val code: String)