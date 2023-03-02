package ru.er.domain.episodes.models

import kotlinx.serialization.Serializable

@Serializable
data class PaginationSeriesList(
    val keyword: String,
    val pagesCount: Int,
    val films: List<SeriesPreviewInfo>
)