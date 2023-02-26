package ru.er.domain.episodes.search

import dagger.Reusable
import javax.inject.Inject

@Reusable
class SearchSeriesUseCase @Inject constructor(
    private val searchSeriesDataSource: SearchSeriesDataSource
) {

    suspend fun getSeriesListByPage(keyword: String, page: Int) =
        searchSeriesDataSource.getSeriesListByPage(keyword, page)


}