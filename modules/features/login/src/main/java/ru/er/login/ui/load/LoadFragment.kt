package ru.er.login.ui.load

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.er.login.R
import ru.er.login.databinding.FragmentHelloBinding

class LoadFragment: Fragment(R.layout.fragment__load) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        findNavController().navigate(R.id.login_graph)
    }

}