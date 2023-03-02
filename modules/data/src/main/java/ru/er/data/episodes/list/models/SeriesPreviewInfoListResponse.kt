package ru.er.data.episodes.list.models

import kotlinx.serialization.Serializable
import ru.er.domain.episodes.models.SeriesPreviewInfo

@Serializable
data class SeriesPreviewInfoListResponse(
    val list: List<SeriesPreviewInfo>
)