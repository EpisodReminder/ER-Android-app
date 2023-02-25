package ru.er.domain.confirmation

import dagger.Reusable
import javax.inject.Inject

@Reusable
class ConfirmationUseCase @Inject constructor(
    private val confirmationDataSource: ConfirmationDataSource
) {

    suspend fun checkConfirmationCode(code: String) =
        confirmationDataSource.checkConfirmationCode(code)

    suspend fun loadDemoMode() = confirmationDataSource.loadDemoMode()


}