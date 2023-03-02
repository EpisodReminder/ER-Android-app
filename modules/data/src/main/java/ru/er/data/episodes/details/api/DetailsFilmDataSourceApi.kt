package ru.er.data.episodes.details.api

import ru.er.domain.episodes.models.FilmDetails

interface DetailsFilmDataSourceApi {
    suspend fun getFilmDetails(id: Int): FilmDetails
}