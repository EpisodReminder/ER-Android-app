package ru.er.login.ui.hello

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.er.domain.confirmation.ConfirmationUseCase
import ru.er.utils.Consumable
import ru.er.utils.post
import javax.inject.Inject

@HiltViewModel
class HelloScreenViewModel @Inject constructor(
    private val confirmationRepository: ConfirmationUseCase,
) : ViewModel() {

    private val _demoModeState =  MutableStateFlow<Consumable<Unit>?>(null)
    val demoModeState: StateFlow<Consumable<Unit>?> get() = _demoModeState.asStateFlow()

    fun loadDemoMode() {
        viewModelScope.launch {
            _demoModeState.post(confirmationRepository.loadDemoMode())
        }
    }
}