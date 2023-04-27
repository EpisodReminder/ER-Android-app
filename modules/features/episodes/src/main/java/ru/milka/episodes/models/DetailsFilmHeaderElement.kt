package ru.milka.episodes.models

import ru.er.core_ui.recycler.ListItem
import ru.er.domain.episodes.models.SeriesPreviewInfo

data class DetailsFilmHeaderElement(
    val kinopoiskId: Int,
    val title: String,
    val imgPreviewLink: String,
    val rating: String,
    val description: String,
    val yearStart: String,
) : ListItem() {

    companion object {
        fun SeriesPreviewInfo.toDetailsFilmHeaderElement() =
            DetailsFilmHeaderElement(
                kinopoiskId = if (kinopoiskId != 0) kinopoiskId else filmId,
                title = this.title,
                imgPreviewLink = this.posterUrlPreview,
                description = if (description.length > 200) "${this.description.slice(0..200)}..." else description,
                rating = if (ratingKinopoisk.isNotEmpty()) ratingKinopoisk else rating,
                yearStart = startYear.toString()
            )
    }

}