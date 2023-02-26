package ru.milka.episodes.ui.main

import ru.er.core_ui.recycler.ListItem
import ru.er.core_ui.recycler.components.LoaderElement
import ru.er.domain.episodes.models.SeriesPreviewInfo
import ru.milka.episodes.models.HeaderEpisodeSearchElement
import ru.milka.episodes.models.PreviewEpisodeElement.Companion.toPreviewEpisodeElement

object MainEpisodeListApplier {

    fun getMainEpisodeList(list: List<SeriesPreviewInfo>): List<ListItem> {
        return getHeaderMainEpisodeList().apply {
            addAll(list.map { it.toPreviewEpisodeElement() })
        }
    }

    fun getLoadingMainEpisodeList(): List<ListItem> = getHeaderMainEpisodeList().apply {
        add(LoaderElement())
    }

    fun getErrorMainEpisodeList(): List<ListItem> = getHeaderMainEpisodeList().apply { }

    private fun getHeaderMainEpisodeList(): MutableList<ListItem> =
        mutableListOf(HeaderEpisodeSearchElement())


}