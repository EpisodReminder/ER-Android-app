package ru.er.data.auth

import ru.er.core.Config
import ru.er.domain.session.LoginInformationDeviceRepository
import ru.er.domain.session.Session
import ru.er.domain.session.SessionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionRepositoryImpl @Inject constructor(
    private val deviceLoginDataSource: LoginInformationDeviceRepository,
    private val config: Config
) : SessionRepository {

    private var sessionInfo = Session.DEFAULT
    override fun getAuthInfoConditionals(): String {
        TODO("Not yet implemented")
    }

    override fun setDemoMode() {
        sessionInfo = Session.DEMO
    }

    override fun isDemoMode(): Boolean = sessionInfo == Session.DEMO


}