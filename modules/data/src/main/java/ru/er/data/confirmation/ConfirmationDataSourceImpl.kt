package ru.er.data.confirmation

import dagger.Reusable
import ru.er.data.confirmation.api.ConfirmationDataSourceApi
import ru.er.domain.confirmation.ConfirmationDataSource
import ru.er.domain.session.SessionRepository
import javax.inject.Inject


@Reusable
class ConfirmationDataSourceImpl @Inject constructor(
    private val confirmationDataSourceApi: ConfirmationDataSourceApi,
    private val sessionRepository: SessionRepository
) : ConfirmationDataSource {

    override suspend fun checkConfirmationCode(code: String) =
        confirmationDataSourceApi.checkConfirmationCode(code)

    override fun loadDemoMode() = sessionRepository.setDemoMode()
}