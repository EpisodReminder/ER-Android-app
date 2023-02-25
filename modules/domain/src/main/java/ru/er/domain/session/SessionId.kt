package ru.er.domain.session

data class SessionId(val telegramId: String) {
    fun isEmpty(): Boolean = this == EMPTY

    fun isDemo(): Boolean = this == DEMO

    companion object {
        val EMPTY = SessionId("")

        val DEMO = SessionId("demo")
    }
}
