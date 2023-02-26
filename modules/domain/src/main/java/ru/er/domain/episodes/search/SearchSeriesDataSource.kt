package ru.er.domain.episodes.search

import ru.er.domain.episodes.models.PaginationSeriesList

interface SearchSeriesDataSource {

    suspend fun getSeriesListByPage(keyword: String, page: Int): PaginationSeriesList

}