package ru.milka.episodes.models

import ru.er.core_ui.recycler.ListItem
import ru.er.domain.episodes.models.SeriesPreviewInfo

data class DetailsFilmHeaderElement(
    val kinopoiskId: Int,
    val title: String,
    val imgPreviewLink: String,
    val ratingKinopoisk: Double,
) : ListItem() {

    companion object {
//        fun SeriesPreviewInfo.toPreviewEpisodeElement() =
//            DetailsFilmHeaderElement(title = this.title, imgPreviewLink = this.posterUrlPreview)
    }

}