package ru.er.domain.episodes.list

import ru.er.domain.episodes.models.SeriesPreviewInfo

interface FavoriteSeriesDataSource {

    suspend fun getFollowList(): List<SeriesPreviewInfo>

    fun unfollow(id: String)

}