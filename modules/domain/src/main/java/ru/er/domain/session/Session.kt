package ru.er.domain.session

data class Session(
    val userId: SessionId
) {
    fun authState(): AuthState = when {
        !userId.isEmpty() -> AuthState.AUTHORIZED
        else -> AuthState.NONE
    }

    companion object {
        val DEFAULT = Session(
            userId = SessionId.EMPTY
        )

        val DEMO = Session(
            userId = SessionId.DEMO
        )

    }
}
