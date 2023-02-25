package ru.er.login.ui.confirmation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import pub.monkeyboss.widget.CodeInputView
import ru.er.core.Config
import ru.er.core_ui.elements.showDefaultErrorAlertDialog
import ru.er.core_ui.elements.showWillBeSoonAlertDialog
import ru.er.login.R
import ru.er.login.databinding.FragmentConfirmationBinding
import ru.er.utils.ActionState
import ru.er.utils.collectWhenStarted
import ru.er.utils.viewScope
import javax.inject.Inject

@AndroidEntryPoint
class ConfirmationFragment : Fragment(R.layout.fragment__confirmation) {

    private val binding: FragmentConfirmationBinding by viewBinding()
    private val viewModel: ConfirmationViewModel by viewModels()

    @Inject
    lateinit var config: Config

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInit()
        observeInit()
    }

    private fun observeInit() {
        viewModel.confirmationCodeState.collectWhenStarted(viewScope, ::confirmationCodeObserver)
    }

    private fun bindingInit() {
        with(binding) {
            buttonBot.setOnClickListener {
                val telegram =
                    Intent(Intent.ACTION_VIEW, Uri.parse(config.telegramBotUrl))
                startActivity(telegram)
            }
            checkButton.setOnClickListener {
                viewModel.confirmCode(codeInputField.input)
            }
            codeInputField.setOnInputListener(CodeInputListener(::updateButton))
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        }
    }

    private fun confirmationCodeObserver(state: ActionState<Boolean>) = when (state) {
        is ActionState.Success -> {
            showWillBeSoonAlertDialog(requireContext())
        }
        is ActionState.Error -> {
            showDefaultErrorAlertDialog(requireContext()) {}
        }
        else -> {}
    }

    private fun updateButton() {
        binding.checkButton.isEnabled = isValidSendButton()
    }

    private fun isValidSendButton() =
        binding.codeInputField.input.length == ConfirmationViewModel.CONFIRMATION_FIELD_SIZE

    private class CodeInputListener(
        private val action: () -> Unit
    ) : CodeInputView.OnInputListener {
        override fun onInput(position: Int, c: Char, content: String?) = action()

        override fun onDelete(content: String?) = action()

        override fun onComplete(content: String?) = action()

    }
}