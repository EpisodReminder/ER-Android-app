package ru.er.login.ui.hello

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.er.core_ui.elements.showWillBeSoonAlertDialog
import ru.er.login.R
import ru.er.login.databinding.FragmentHelloBinding
import ru.er.utils.collectConsumableWhenStarted
import ru.er.utils.viewScope

@AndroidEntryPoint
class HelloFragment : Fragment(R.layout.fragment__hello) {

    private val binding: FragmentHelloBinding by viewBinding()
    private val viewModel: HelloScreenViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInit()
        observeInit()
    }

    private fun observeInit() {
        viewModel.demoModeState.collectConsumableWhenStarted(viewScope) {
            showWillBeSoonAlertDialog(requireContext())
        }
    }

    private fun bindingInit() {
        with (binding) {
            loginButton.setOnClickListener {
                findNavController().navigate(R.id.confirmation_screen)
            }
            demoButton.setOnClickListener {
                viewModel.loadDemoMode()
            }
        }
    }

}