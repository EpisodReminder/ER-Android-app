package ru.er.reminder

import ru.er.core.Config
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppConfig @Inject constructor() : Config {

    override val baseApiUrl: String = BuildConfig.BASE_URL
    override val telegramBotUrl: String = BuildConfig.TELEGRAM_BOT_URL
    override val kinopoiskUrl: String = BuildConfig.KINOPOISK_URL
    override val demoModeDelay: Long = BuildConfig.DEMO_MODE_RESPONSE_DELAY

}