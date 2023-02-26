package ru.er.data.episodes.list.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.data.episodes.list.FavoriteSeriesDataSourceImpl
import ru.er.domain.episodes.list.FavoriteSeriesDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteSeriesDataSourceModel {

    @Binds
    @Reusable
    abstract fun bindFavoriteSeriesDataSource(impl: FavoriteSeriesDataSourceImpl): FavoriteSeriesDataSource

}