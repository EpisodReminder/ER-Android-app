package ru.er.data.episodes.list.api

import android.content.Context
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import ru.er.core.Config
import ru.er.core.decode
import ru.er.data.episodes.list.models.SeriesPreviewInfoListResponse
import ru.er.domain.episodes.models.SeriesPreviewInfo
import javax.inject.Inject

@Reusable
class FavoriteSeriesDataSourceApiDemoImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val config: Config,
    private val json: Json
) : FavoriteSeriesDataSourceApi {
    override suspend fun getFavoriteList(): List<SeriesPreviewInfo> {
        delay(config.demoModeDelay)
        return context.assets.decode<SeriesPreviewInfoListResponse>(json, "episodes/list.json").list
    }
}