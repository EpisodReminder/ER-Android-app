package ru.milka.episodes.models

import ru.er.core_ui.recycler.ListItem
import ru.er.domain.episodes.models.SeriesPreviewInfo

data class PreviewEpisodeElement(
    val kinopoiskId: Int,
    val title: String,
    val imgPreviewLink: String
) : ListItem() {

    companion object {
        fun SeriesPreviewInfo.toPreviewEpisodeElement() =
            PreviewEpisodeElement(kinopoiskId=this.filmId, title = this.title, imgPreviewLink = this.posterUrlPreview)
    }

}