package ru.er.reminder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.er.reminder.navigation.EpisodeNavigationInterfaceImpl
import ru.er.reminder.navigation.LoginNavigationInterfaceImpl
import ru.milka.navigation.EpisodeNavigationInterface
import ru.milka.navigation.LoginNavigationInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    @Singleton
    fun bindLoginNavigationInterface(impl: LoginNavigationInterfaceImpl): LoginNavigationInterface

    @Binds
    @Singleton
    fun bindEpisodeNavigationInterface(impl: EpisodeNavigationInterfaceImpl): EpisodeNavigationInterface

}