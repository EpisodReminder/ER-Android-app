package ru.er.data.episodes.details

import dagger.Reusable
import ru.er.data.episodes.details.api.DetailsFilmDataSourceApi
import ru.er.domain.episodes.details.DetailsFilmDataSource
import ru.er.domain.episodes.models.FilmDetails
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class DetailsFilmDataSourceImpl @Inject constructor(
    private val detailsFilmDataSourceApi: DetailsFilmDataSourceApi
) : DetailsFilmDataSource {


    override suspend fun getFollowList(id: Int): SeriesPreviewInfo =
        detailsFilmDataSourceApi.getFilmDetails(id)

}