package ru.er.domain.episodes.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SeriesPreviewInfo(
    val kinopoiskId: Int = 0,
    val filmId: Int = 0,
    @SerialName("nameRu")
    val title: String,
    val posterUrlPreview: String,
    val description: String,
    @SerialName("ratingKinopoisk")
    val ratingKinopoisk: String = "",
    val rating: String = "",
    val seasonInfo: SeriesInfoInfo? = null,
    val slogan: String = "",
    val startYear: Int = -1,
)
