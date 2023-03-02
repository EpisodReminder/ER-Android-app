package ru.er.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

data class NavCommand(
    val action: Int = -1,
    val direction: NavDirections? = null,
    var args: Bundle? = null,
    val navOptions: NavOptions? = null
)

fun Fragment.navigate(navCommand: NavCommand) {
    findNavController().navigate(navCommand)
}

fun NavController.navigate(navCommand: NavCommand) {
    if (navCommand.direction != null) navigate(navCommand.direction)
    else navigate(navCommand.action, navCommand.args, navCommand.navOptions)
}