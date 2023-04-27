package ru.milka.episodes.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import ru.er.core.Config
import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.components.emptyDelegateAdapter
import ru.er.core_ui.recycler.components.episodeCellDelegateAdapter
import ru.er.core_ui.recycler.components.loaderDelegateAdapter
import ru.er.core_ui.recycler.components.textCellDelegateAdapter
import ru.er.utils.collectConsumableWhenStarted
import ru.er.utils.navigate
import ru.er.utils.viewScope
import ru.milka.episodes.R
import ru.milka.episodes.databinding.FragmentDetailsFilmBinding
import ru.milka.episodes.ui.details.delegates.headerEpisodesDetailsFilmDelegateAdapter
import ru.milka.navigation.EpisodeNavigationInterface
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFilmFragment : Fragment(R.layout.fragment__details_film) {
    private val binding: FragmentDetailsFilmBinding by viewBinding()
    private val viewModel: DetailsFilmViewModel by viewModels()
    private val args: DetailsFilmFragmentArgs by navArgs()


    @Inject
    lateinit var navigator: EpisodeNavigationInterface

    @Inject
    lateinit var config: Config

    private val recyclerAdapter: ListDelegationAdapter<List<ListItem>> by lazy {
        ListDelegationAdapter(
            headerEpisodesDetailsFilmDelegateAdapter(
                kinopoiskClick = ::kinopoiskClick,
                switchClick = ::followSwitchChange
            ),
            textCellDelegateAdapter(),
            loaderDelegateAdapter(),
            emptyDelegateAdapter(),
            episodeCellDelegateAdapter()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInit()
        observeInit()

        viewModel.setFilmId(args.kinopoiskId)
    }

    private fun observeInit() {
        viewModel.listState.collectConsumableWhenStarted(viewScope, ::recyclerListObserver)
    }

    private fun bindingInit() {
        with(binding) {
            recycler.adapter = recyclerAdapter
            toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        }

    }

    private fun recyclerListObserver(list: List<ListItem>?) {
        recyclerAdapter.items = list
        recyclerAdapter.notifyDataSetChanged()
    }

    private fun kinopoiskClick(kinopoiskId: Int) {
        val kinopoisk =
            Intent(Intent.ACTION_VIEW, Uri.parse("${config.kinopoiskUrl}/$kinopoiskId"))
        startActivity(kinopoisk)
    }

    private fun followSwitchChange(bool: Boolean) {

    }

    private fun headerClickNavigate() {
        navigate(navigator.toSearchScreen())
    }
}