package ru.er.data.episodes.list.di

import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.data.confirmation.api.ConfirmationDataSourceApi
import ru.er.data.confirmation.api.ConfirmationDataSourceApiImpl
import ru.er.data.episodes.list.api.FavoriteSeriesDataSourceApi
import ru.er.data.episodes.list.api.FavoriteSeriesDataSourceApiDemoImpl
import ru.er.data.episodes.list.api.FavoriteSeriesDataSourceApiImpl
import ru.er.domain.session.SessionRepository

@Module
@InstallIn(SingletonComponent::class)
internal object FavoriteSeriesDataSourceApiModel {

    @Provides
    @Reusable
    fun bindFavoriteSeriesDataSourceApi(
        session: SessionRepository,
        demo: Lazy<FavoriteSeriesDataSourceApiDemoImpl>,
        impl: Lazy<FavoriteSeriesDataSourceApiImpl>
    ): FavoriteSeriesDataSourceApi = if (session.isDemoMode()) demo.get() else impl.get()

}