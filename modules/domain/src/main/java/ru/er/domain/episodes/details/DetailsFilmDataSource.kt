package ru.er.domain.episodes.details

import ru.er.domain.episodes.models.FilmDetails
import ru.er.domain.episodes.models.SeriesPreviewInfo

interface DetailsFilmDataSource {

    suspend fun getFollowList(id: Int): SeriesPreviewInfo

}