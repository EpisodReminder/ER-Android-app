package ru.er.data.episodes.details.api

import dagger.Reusable
import ru.er.domain.episodes.models.FilmDetails
import ru.er.domain.episodes.models.PaginationSeriesList
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class DetailsFilmDataSourceApiImpl @Inject constructor(): DetailsFilmDataSourceApi {

    override suspend fun getFilmDetails(id: Int): SeriesPreviewInfo {
        TODO("Not yet implemented")
    }
}