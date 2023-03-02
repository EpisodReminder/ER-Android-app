package ru.er.domain.episodes.models

import kotlinx.serialization.Serializable


@Serializable
data class SeriesPreviewInfo(
    val filmId: Int,
    val title: String,
    val posterUrlPreview: String,
)
