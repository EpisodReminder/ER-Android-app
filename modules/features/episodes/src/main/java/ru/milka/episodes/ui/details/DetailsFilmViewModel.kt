package ru.milka.episodes.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.components.LoaderElement
import ru.er.domain.episodes.details.DetailsFilmUseCase
import ru.er.domain.episodes.models.FilmDetails
import ru.er.utils.ActionState
import ru.er.utils.Consumable
import ru.er.utils.launchTo
import ru.er.utils.post
import ru.milka.episodes.ui.details.FilmDetailsListApplier.getFilmDetailsList
import ru.milka.episodes.ui.main.MainEpisodeListApplier.getMainEpisodeList
import javax.inject.Inject

@HiltViewModel
class DetailsFilmViewModel @Inject constructor(
    private val detailsFilmUseCase: DetailsFilmUseCase
) : ViewModel() {

    private val _subscribeSeriesList =
        MutableStateFlow<ActionState<FilmDetails>>(ActionState.Idle())


    private var filmId: Int = 0
    private val _listState = MutableStateFlow<Consumable<List<ListItem>>?>(null)
    val listState: StateFlow<Consumable<List<ListItem>>?> get() = _listState.asStateFlow()

    init {
        observeInit()
    }

    fun setFilmId(id: Int) {
        filmId = id
        loadFilmDetails()
    }

    fun loadFilmDetails() {
        viewModelScope.launchTo(_subscribeSeriesList, context = Dispatchers.IO) {
            detailsFilmUseCase.filmDetails(filmId)
        }
    }

    private fun observeInit() {
        viewModelScope.launch {
            _subscribeSeriesList.asStateFlow().collect {
                _listState.post(
                    when (it) {
                        is ActionState.Success -> getFilmDetailsList(it.data)
                        is ActionState.Error -> listOf()
                        is ActionState.Loading -> listOf(LoaderElement())
                        else -> mutableListOf()
                    }
                )
            }
        }
    }


}