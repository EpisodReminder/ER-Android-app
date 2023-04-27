package ru.milka.episodes.ui.details

import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.components.EmptyCell
import ru.er.core_ui.recycler.components.EpisodeCellElement
import ru.er.core_ui.recycler.components.TextCellElement
import ru.er.domain.episodes.models.SeriesPreviewInfo
import ru.milka.episodes.models.DetailsFilmHeaderElement.Companion.toDetailsFilmHeaderElement

object FilmDetailsListApplier {

    fun getFilmDetailsList(details: SeriesPreviewInfo): List<ListItem> = mutableListOf<ListItem>(
        details.toDetailsFilmHeaderElement()
    ).apply {
        add(EmptyCell())
        add(
            TextCellElement(
                title = "Описание",
                description = details.description
            )
        )
        add(EmptyCell())
        add(EmptyCell())
        add(EmptyCell())

        details.seasonInfo?.let { info ->
            info.items.forEach { season ->
                val seasonNumb = season.number
                season.episodes.forEach {
                    add(
                        EpisodeCellElement(
                            title = seasonNumb.toString(),
                            description = it.episodeNumber.toString(),
                            name = it.nameRu,
                            date = it.releaseDate.split("-").let { listOf(it[1], it[2], it[0]).joinToString(".") }
                        )
                    )
                    add(EmptyCell())
                }
            }

        }
    }
}