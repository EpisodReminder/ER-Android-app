package ru.er.data.confirmation.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.data.confirmation.ConfirmationDataSourceImpl
import ru.er.domain.confirmation.ConfirmationDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfirmationDataRepositoryModel {

    @Binds
    @Reusable
    abstract fun bindConfirmationDataSource(impl: ConfirmationDataSourceImpl): ConfirmationDataSource

}