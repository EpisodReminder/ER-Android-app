package ru.milka.episodes.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.components.loaderDelegateAdapter
import ru.er.utils.collectConsumableWhenStarted
import ru.er.utils.navigate
import ru.er.utils.viewScope
import ru.milka.episodes.R
import ru.milka.episodes.databinding.FragmentMainEpisodesBinding
import ru.milka.episodes.ui.main.delegates.headerEpisodesDelegateAdapter
import ru.milka.episodes.ui.main.delegates.previewEpisodeDelegateAdapter
import ru.milka.navigation.EpisodeNavigationInterface
import javax.inject.Inject

@AndroidEntryPoint
class MainEpisodesFragment : Fragment(R.layout.fragment__main_episodes) {
    private val binding: FragmentMainEpisodesBinding by viewBinding()
    private val viewModel: MainEpisodesViewModel by viewModels()

    @Inject
    lateinit var navigator: EpisodeNavigationInterface

    private val recyclerAdapter: ListDelegationAdapter<List<ListItem>> by lazy {
        ListDelegationAdapter(
            previewEpisodeDelegateAdapter(:: detailsFilmClick),
            headerEpisodesDelegateAdapter(::headerClickNavigate),
            loaderDelegateAdapter()
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingInit()
        observeInit()
    }

    private fun observeInit() {
        viewModel.listState.collectConsumableWhenStarted(viewScope, ::recyclerListObserver)
    }

    private fun bindingInit() {
        with(binding) {
            recycler.adapter = recyclerAdapter
        }

    }

    private fun recyclerListObserver(list: List<ListItem>?) {
        recyclerAdapter.items = list
        recyclerAdapter.notifyDataSetChanged()
    }

    private fun detailsFilmClick(kinopoiskId: Int) {
        navigate(navigator.toDetailsFilmScreen(kinopoiskId))
    }

    private fun headerClickNavigate() {
        navigate(navigator.toSearchScreen())
    }
}