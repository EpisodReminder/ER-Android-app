package ru.er.data.episodes.list.api

import dagger.Reusable
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.delay
import ru.er.core.Config
import ru.er.data.episodes.list.models.SeriesPreviewInfoListResponse
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class FavoriteSeriesDataSourceApiImpl @Inject constructor(
    private val config: Config,
    private val client: HttpClient
) : FavoriteSeriesDataSourceApi {
    override suspend fun getFavoriteList(): List<SeriesPreviewInfo> {
        delay(config.demoModeDelay)
        return client.get<SeriesPreviewInfoListResponse>("${config.baseApiUrl}/subscribes/list") {
            contentType(ContentType.Application.Json)
        }.list
    }
}