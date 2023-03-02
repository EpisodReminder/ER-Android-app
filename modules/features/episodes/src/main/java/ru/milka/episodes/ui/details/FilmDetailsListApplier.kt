package ru.milka.episodes.ui.details

import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.components.TextCellElement
import ru.er.domain.episodes.models.FilmDetails
import ru.milka.episodes.R
import ru.milka.episodes.models.DetailsFilmHeaderElement

object FilmDetailsListApplier {

    fun getFilmDetailsList(details: FilmDetails): List<ListItem> = mutableListOf<ListItem>(
        DetailsFilmHeaderElement(
            kinopoiskId = details.kinopoiskId,
            title = details.title,
            imgPreviewLink = details.posterUrlPreview,
            ratingKinopoisk = details.ratingKinopoisk
        )
    ).apply {
        add(TextCellElement(titleRes = R.string.film_details__description, description = details.description))
        add(TextCellElement(titleRes = R.string.film_details__slogan, description = details.slogan))
        add(TextCellElement(title = "Any title text cell", description = "Any text"))
    }
}