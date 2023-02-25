package ru.er.login.ui.confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.er.domain.confirmation.ConfirmationUseCase
import ru.er.utils.ActionState
import ru.er.utils.launchTo
import javax.inject.Inject

@HiltViewModel
class ConfirmationViewModel @Inject constructor(
    private val confirmationRepository: ConfirmationUseCase,
) : ViewModel() {

    private val _confirmationCodeState = MutableStateFlow<ActionState<Boolean>>(ActionState.Idle())
    val confirmationCodeState get() = _confirmationCodeState.asStateFlow()

    private val _inputConfirmCodeState = MutableStateFlow<ActionState.Error<String>?>(null)

    fun confirmCode(code: String) {
        viewModelScope.launchTo(_confirmationCodeState) {
            confirmationRepository.checkConfirmationCode(code)
        }
    }

    companion object {
        const val CONFIRMATION_FIELD_SIZE = 6
    }

}