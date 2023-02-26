package ru.milka.episodes.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import dagger.hilt.android.AndroidEntryPoint
import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.PaginationScrollListener
import ru.er.core_ui.recycler.components.loaderDelegateAdapter
import ru.er.utils.collectConsumableWhenStarted
import ru.er.utils.navigate
import ru.er.utils.viewScope
import ru.milka.episodes.R
import ru.milka.episodes.databinding.FragmentSearchSeriesBinding
import ru.milka.episodes.ui.main.delegates.headerEpisodesDelegateAdapter
import ru.milka.episodes.ui.main.delegates.previewEpisodeDelegateAdapter
import ru.milka.navigation.EpisodeNavigationInterface
import javax.inject.Inject

@AndroidEntryPoint
class SearchSeriesFragment : Fragment(R.layout.fragment__search_series) {
    private val binding: FragmentSearchSeriesBinding by viewBinding()
    private val viewModel: SearchSeriesViewModel by viewModels()

    @Inject
    lateinit var navigator: EpisodeNavigationInterface

    private val recyclerAdapter: ListDelegationAdapter<List<ListItem>> by lazy {
        ListDelegationAdapter(
            previewEpisodeDelegateAdapter(::detailsFilmClick),
            headerEpisodesDelegateAdapter(::headerClickNavigate),
            loaderDelegateAdapter()
        )
    }

    private val paginationScrollListener: PaginationScrollListener by lazy {
        object : PaginationScrollListener(binding.recycler.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean = viewModel.isEndPage

            override fun isLoading(): Boolean = viewModel.isLoadingPage

            override fun loadMoreItems() = viewModel.loadNewPage()
        }
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
        binding.recycler.adapter = recyclerAdapter
        binding.recycler.addOnScrollListener(paginationScrollListener)

        binding.searchButton.setOnClickListener {
            viewModel.loadNewSeries(binding.editTextSearch.text?.toString().orEmpty())
        }
        binding.editTextSearch.requestFocus()
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
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
        //navigate(nav)
    }
}