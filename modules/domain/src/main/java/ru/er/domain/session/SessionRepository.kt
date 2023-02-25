package ru.er.domain.session

interface SessionRepository {

    fun getAuthInfoConditionals(): String

    fun setDemoMode()

}