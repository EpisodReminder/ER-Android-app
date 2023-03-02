package ru.er.data.episodes.search.di

import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.data.episodes.search.api.SearchSeriesDataSourceApi
import ru.er.data.episodes.search.api.SearchSeriesDataSourceApiDemoImpl
import ru.er.data.episodes.search.api.SearchSeriesDataSourceApiImpl
import ru.er.domain.session.SessionRepository

@Module
@InstallIn(SingletonComponent::class)
internal object SearchSeriesDataSourceApiModel {

    @Provides
    @Reusable
    fun bindSearchSeriesDataSourceApi(
        session: SessionRepository,
        demo: Lazy<SearchSeriesDataSourceApiDemoImpl>,
        impl: Lazy<SearchSeriesDataSourceApiImpl>
    ): SearchSeriesDataSourceApi = if (session.isDemoMode()) demo.get() else impl.get()

}