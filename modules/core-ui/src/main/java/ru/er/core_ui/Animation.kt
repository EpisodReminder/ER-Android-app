package ru.er.core_ui

import androidx.navigation.AnimBuilder

val fadeAnimation: (AnimBuilder) -> Unit = {
    with(it) {
        enter = android.R.animator.fade_in
        exit = android.R.animator.fade_out
        popEnter = android.R.animator.fade_in
        popExit = android.R.animator.fade_out
    }
}

val slideAnimation: (AnimBuilder) -> Unit = {
    with(it) {
        enter = R.anim.slide_in
        exit = android.R.animator.fade_out
        popEnter = android.R.animator.fade_in
        popExit = R.anim.slide_out
    }
}


