package ru.er.data.auth

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.domain.session.LoginInformationDeviceRepository
import ru.er.domain.session.SessionRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthDataRepositoryModel {

    @Binds
    @Singleton
    abstract fun bindLoginInformationDeviceRepository(impl: AuthInformationDataSource): LoginInformationDeviceRepository

    @Binds
    @Singleton
    abstract fun bindSessionRepository(impl: SessionRepositoryImpl): SessionRepository

}