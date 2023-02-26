package ru.er.reminder.navigation

import ru.er.utils.NavCommand
import ru.milka.episodes.ui.details.DetailsFilmFragmentArgs
import ru.milka.navigation.EpisodeNavigationInterface
import javax.inject.Inject

class EpisodeNavigationInterfaceImpl @Inject constructor() : EpisodeNavigationInterface {


    override fun toSearchScreen(): NavCommand = NavCommand(
        action = ru.milka.episodes.R.id.search_screen,
    )

    override fun toDetailsFilmScreen(kinopoiskId: Int): NavCommand = NavCommand(
        action = ru.milka.episodes.R.id.details_screen,
        args = DetailsFilmFragmentArgs(kinopoiskId = kinopoiskId).toBundle()
    )


}