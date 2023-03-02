package ru.er.data.episodes.details.api

import android.content.Context
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import ru.er.core.Config
import ru.er.core.decode
import ru.er.domain.episodes.models.FilmDetails
import ru.er.domain.episodes.models.PaginationSeriesList
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class DetailsFilmDataSourceApiDemoImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val config: Config,
    private val json: Json
): DetailsFilmDataSourceApi {

    override suspend fun getFilmDetails(id: Int): FilmDetails {
        delay(config.demoModeDelay)
        return context.assets.decode(json, "episodes/details.json")

    }
}