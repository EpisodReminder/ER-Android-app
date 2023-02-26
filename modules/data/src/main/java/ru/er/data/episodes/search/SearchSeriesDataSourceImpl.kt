package ru.er.data.episodes.search

import dagger.Reusable
import ru.er.data.episodes.search.api.SearchSeriesDataSourceApi
import ru.er.domain.episodes.search.SearchSeriesDataSource
import javax.inject.Inject

@Reusable
class SearchSeriesDataSourceImpl @Inject constructor(
    private val searchDataSourceApi: SearchSeriesDataSourceApi
) : SearchSeriesDataSource {

    override suspend fun getSeriesListByPage(keyword: String, page: Int) =
        searchDataSourceApi.getSeriesListByPage(keyword, page)

}