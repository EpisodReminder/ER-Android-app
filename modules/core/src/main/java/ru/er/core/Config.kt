package ru.er.core

interface Config {
    val baseApiUrl: String
    val telegramBotUrl: String
    val kinopoiskUrl: String
    val demoModeDelay: Long
}