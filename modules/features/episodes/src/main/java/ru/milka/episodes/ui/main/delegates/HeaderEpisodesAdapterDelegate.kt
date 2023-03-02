package ru.milka.episodes.ui.main.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.er.core_ui.recycler.ListItem
import ru.milka.episodes.databinding.ItemHeaderEpisodesSearchBinding
import ru.milka.episodes.models.HeaderEpisodeSearchElement

fun headerEpisodesDelegateAdapter(clickAction: () -> Unit) =
    adapterDelegateViewBinding<HeaderEpisodeSearchElement, ListItem, ItemHeaderEpisodesSearchBinding>(
        { layoutInflater, root ->
            ItemHeaderEpisodesSearchBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

        bind {
            binding.editTextSearch.setOnFocusChangeListener { _, b -> if (b) clickAction() }
        }
    }