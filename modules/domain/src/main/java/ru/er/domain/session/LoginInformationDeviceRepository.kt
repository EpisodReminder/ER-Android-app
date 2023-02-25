package ru.er.domain.session


interface LoginInformationDeviceRepository {

    fun getLogin(): String?

    fun getPassword() : String?

    fun putLogin(login: String)

    fun putPassword(password: String)

    fun removeLogin()

    fun removePassword()

}