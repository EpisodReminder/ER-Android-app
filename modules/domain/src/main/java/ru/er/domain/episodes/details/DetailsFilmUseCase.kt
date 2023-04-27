package ru.er.domain.episodes.details

import dagger.Reusable
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class DetailsFilmUseCase @Inject constructor(
    private val detailsFilmDataSource: DetailsFilmDataSource
) {

    suspend fun filmDetails(id: Int): SeriesPreviewInfo = detailsFilmDataSource.getFollowList(id)


}