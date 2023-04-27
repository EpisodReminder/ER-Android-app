package ru.er.domain.episodes.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class SeriesInfoInfo(
    val total: Int = 0,
    val items: List<SeasonModel>
)

@Serializable
data class SeasonModel(
    val number: Int = 0,
    val episodes: List<EpisodeModel>
)

@Serializable
data class EpisodeModel(
    val seasonNumber: Int = 1,
    val episodeNumber: Int = 1,
    val nameRu: String = "",
    val releaseDate: String = "",
)
