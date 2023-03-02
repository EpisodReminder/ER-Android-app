package ru.er.data.episodes.search.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.data.episodes.list.FavoriteSeriesDataSourceImpl
import ru.er.data.episodes.search.SearchSeriesDataSourceImpl
import ru.er.domain.episodes.list.FavoriteSeriesDataSource
import ru.er.domain.episodes.search.SearchSeriesDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class SearchSeriesDataSourceModel {

    @Binds
    @Reusable
    abstract fun bindSearchSeriesDataSource(impl: SearchSeriesDataSourceImpl): SearchSeriesDataSource

}