package ru.er.domain.episodes.details

import dagger.Reusable
import javax.inject.Inject

@Reusable
class DetailsFilmUseCase @Inject constructor(
    private val detailsFilmDataSource: DetailsFilmDataSource
) {

    suspend fun filmDetails(id: Int) = detailsFilmDataSource.getFollowList(id)


}