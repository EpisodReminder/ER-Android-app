package ru.milka.episodes.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.er.core_ui.recycler.ListItem
import ru.er.domain.episodes.list.FavoriteSeriesUseCase
import ru.er.domain.episodes.models.SeriesPreviewInfo
import ru.er.utils.ActionState
import ru.er.utils.Consumable
import ru.er.utils.launchTo
import ru.er.utils.post
import ru.milka.episodes.ui.main.MainEpisodeListApplier.getErrorMainEpisodeList
import ru.milka.episodes.ui.main.MainEpisodeListApplier.getLoadingMainEpisodeList
import ru.milka.episodes.ui.main.MainEpisodeListApplier.getMainEpisodeList
import javax.inject.Inject

@HiltViewModel
class MainEpisodesViewModel @Inject constructor(
    private val favoriteSeriesUseCase: FavoriteSeriesUseCase
) : ViewModel() {

    private val _subscribeSeriesList =
        MutableStateFlow<ActionState<List<SeriesPreviewInfo>>>(ActionState.Idle())


    private val _listState = MutableStateFlow<Consumable<List<ListItem>>?>(null)
    val listState: StateFlow<Consumable<List<ListItem>>?> get() = _listState.asStateFlow()

    init {
        observeInit()
        loadFavoriteList()
    }

    fun loadFavoriteList() {
        viewModelScope.launchTo(_subscribeSeriesList, context = Dispatchers.IO) {
            favoriteSeriesUseCase.favoriteSeriesList()
        }
    }

    private fun observeInit() {
        viewModelScope.launch {
            _subscribeSeriesList.asStateFlow().collect {
                _listState.post(
                    when (it) {
                        is ActionState.Success -> getMainEpisodeList(it.data)
                        is ActionState.Error -> getErrorMainEpisodeList()
                        is ActionState.Loading -> getLoadingMainEpisodeList()
                        else -> mutableListOf()
                    }
                )
            }
        }
    }


}