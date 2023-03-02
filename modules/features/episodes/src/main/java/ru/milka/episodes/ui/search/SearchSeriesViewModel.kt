package ru.milka.episodes.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.PaginationState
import ru.er.core_ui.recycler.components.LoaderElement
import ru.er.domain.episodes.models.PaginationSeriesList
import ru.er.domain.episodes.search.SearchSeriesUseCase
import ru.er.utils.ActionState
import ru.er.utils.Consumable
import ru.er.utils.launchTo
import ru.er.utils.post
import ru.milka.episodes.models.PreviewEpisodeElement.Companion.toPreviewEpisodeElement
import javax.inject.Inject

@HiltViewModel
class SearchSeriesViewModel @Inject constructor(
    private val searchSeriesUseCase: SearchSeriesUseCase
) : ViewModel() {

    private val _subscribeSeriesList =
        MutableStateFlow<ActionState<PaginationSeriesList>>(ActionState.Idle())

    private var pageNumber: Int = 1
    private var keywords: String = ""

    private var _paginationState: PaginationState = PaginationState.IDLE
    val isLoadingPage get() = _paginationState == PaginationState.LOADING
    val isEndPage get() = _paginationState == PaginationState.END

    private var searchJob: Job? = null

    private val _listState = MutableStateFlow<Consumable<List<ListItem>>?>(null)
    val listState: StateFlow<Consumable<List<ListItem>>?> get() = _listState.asStateFlow()

    init {
        observeInit()
    }

    fun loadNewPage() {
        searchJob?.cancel()

        searchJob = viewModelScope.launchTo(_subscribeSeriesList, context = Dispatchers.IO) {
            searchSeriesUseCase.getSeriesListByPage(keywords, pageNumber)
        }
    }

    fun loadNewSeries(keywords: String) {
        if (keywords.isBlank()) return

        searchJob?.cancel()
        this.keywords = keywords
        pageNumber = 1
        // viewModelScope???
        _listState.post(mutableListOf())

        searchJob = viewModelScope.launchTo(_subscribeSeriesList, context = Dispatchers.IO) {
            searchSeriesUseCase.getSeriesListByPage(keywords, pageNumber)
        }

    }


    private fun observeInit() {
        viewModelScope.launch {
            _subscribeSeriesList.asStateFlow().collect { it ->
                val previousList = _listState.value?.data?.toMutableList() ?: mutableListOf()
                previousList.addAll(
                    when (it) {
                        is ActionState.Success -> {
                            it.data.films.map { it.toPreviewEpisodeElement() }
                        }
                        is ActionState.Error -> listOf()
                        is ActionState.Loading -> listOf(LoaderElement())
                        else -> mutableListOf()
                    }
                )

                if (it is ActionState.Loading) _paginationState = PaginationState.LOADING
                else {
                    if (it is ActionState.Success) {
                        if (it.data.pagesCount == pageNumber) _paginationState = PaginationState.END
                        else pageNumber++
                    }
                    if (_paginationState != PaginationState.END) _paginationState =
                        PaginationState.IDLE
                    previousList.removeAll { it is LoaderElement }
                }

                _listState.post(previousList)
            }
        }
    }


}