package ru.er.reminder.navigation

import androidx.navigation.PopUpToBuilder
import androidx.navigation.navOptions
import ru.er.utils.NavCommand
import ru.milka.navigation.LoginNavigationInterface
import javax.inject.Inject

class LoginNavigationInterfaceImpl @Inject constructor() : LoginNavigationInterface {

    override fun toEpisodeScreen(): NavCommand = NavCommand(
        action = ru.milka.episodes.R.id.episodes_graph,
        navOptions = navOptions {
            popUpTo(ru.er.login.R.id.login_graph) { PopUpToBuilder().apply { inclusive = true } }
        }
    )

}