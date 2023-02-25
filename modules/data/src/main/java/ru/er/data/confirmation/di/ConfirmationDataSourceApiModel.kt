package ru.er.data.confirmation.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.data.confirmation.api.ConfirmationDataSourceApi
import ru.er.data.confirmation.api.ConfirmationDataSourceApiImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ConfirmationDataSourceApiModel {

    @Binds
    @Reusable
    abstract fun bindConfirmationDataSourceApi(impl: ConfirmationDataSourceApiImpl): ConfirmationDataSourceApi

}