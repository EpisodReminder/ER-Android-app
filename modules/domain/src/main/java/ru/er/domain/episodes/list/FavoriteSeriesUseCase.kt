package ru.er.domain.episodes.list

import dagger.Reusable
import javax.inject.Inject

@Reusable
class FavoriteSeriesUseCase @Inject constructor(
    private val favoriteSeriesDataSource: FavoriteSeriesDataSource
) {

    suspend fun favoriteSeriesList() = favoriteSeriesDataSource.getFollowList()


}