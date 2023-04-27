package ru.er.data.episodes.details.api

import ru.er.domain.episodes.models.FilmDetails
import ru.er.domain.episodes.models.SeriesPreviewInfo

interface DetailsFilmDataSourceApi {
    suspend fun getFilmDetails(id: Int): SeriesPreviewInfo
}