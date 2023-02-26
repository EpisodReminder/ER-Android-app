package ru.er.data.episodes.search.api

import ru.er.domain.episodes.models.PaginationSeriesList

interface SearchSeriesDataSourceApi {
    suspend fun getSeriesListByPage(keyword: String, page: Int): PaginationSeriesList
}