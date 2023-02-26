package ru.er.data.episodes.search.api

import dagger.Reusable
import ru.er.domain.episodes.models.PaginationSeriesList
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class SearchSeriesDataSourceApiImpl @Inject constructor(): SearchSeriesDataSourceApi {
    override suspend fun getSeriesListByPage(keyword: String, page: Int): PaginationSeriesList {
        TODO("Not yet implemented")
    }
}