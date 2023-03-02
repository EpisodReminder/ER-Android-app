package ru.er.data.episodes.list.api

import ru.er.domain.episodes.models.SeriesPreviewInfo

interface FavoriteSeriesDataSourceApi {

    suspend fun getFavoriteList(): List<SeriesPreviewInfo>

}