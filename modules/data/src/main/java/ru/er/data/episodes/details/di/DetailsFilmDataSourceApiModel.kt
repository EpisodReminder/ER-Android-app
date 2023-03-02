package ru.er.data.episodes.details.di

import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.data.episodes.details.api.DetailsFilmDataSourceApi
import ru.er.data.episodes.details.api.DetailsFilmDataSourceApiDemoImpl
import ru.er.data.episodes.details.api.DetailsFilmDataSourceApiImpl
import ru.er.data.episodes.search.api.SearchSeriesDataSourceApi
import ru.er.data.episodes.search.api.SearchSeriesDataSourceApiDemoImpl
import ru.er.data.episodes.search.api.SearchSeriesDataSourceApiImpl
import ru.er.domain.session.SessionRepository

@Module
@InstallIn(SingletonComponent::class)
internal object DetailsFilmDataSourceApiModel {

    @Provides
    @Reusable
    fun bindDetailsFilmDataSourceApi(
        session: SessionRepository,
        demo: Lazy<DetailsFilmDataSourceApiDemoImpl>,
        impl: Lazy<DetailsFilmDataSourceApiImpl>
    ): DetailsFilmDataSourceApi = if (session.isDemoMode()) demo.get() else impl.get()

}