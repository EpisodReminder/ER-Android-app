package ru.er.domain.episodes.models

import kotlinx.serialization.Serializable


@Serializable
data class FilmDetails(
    val kinopoiskId: Int,
    val title: String,
    val posterUrlPreview: String,
    val ratingKinopoisk: Double,
    val slogan: String,
    val description: String
)
