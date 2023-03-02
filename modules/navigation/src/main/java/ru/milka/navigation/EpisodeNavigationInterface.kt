package ru.milka.navigation

import ru.er.utils.NavCommand

interface EpisodeNavigationInterface {

    fun toSearchScreen(): NavCommand

    fun toDetailsFilmScreen(kinopoiskId: Int): NavCommand

}