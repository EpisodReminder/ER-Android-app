package ru.er.data.episodes.list

import dagger.Reusable
import ru.er.data.episodes.list.api.FavoriteSeriesDataSourceApi
import ru.er.domain.episodes.list.FavoriteSeriesDataSource
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class FavoriteSeriesDataSourceImpl @Inject constructor(
    private val favoriteSeriesDataSourceApi: FavoriteSeriesDataSourceApi,
) : FavoriteSeriesDataSource {
    override suspend fun getFollowList(): List<SeriesPreviewInfo> = favoriteSeriesDataSourceApi.getFavoriteList()

    override fun unfollow(id: String) {
        TODO("Not yet implemented")
    }
}