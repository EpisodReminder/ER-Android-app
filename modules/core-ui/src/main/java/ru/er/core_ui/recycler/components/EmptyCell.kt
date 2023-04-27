package ru.er.core_ui.recycler.components

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.er.core_ui.databinding.ItemEmptyBinding
import ru.er.core_ui.databinding.ItemLoaderBinding
import ru.er.core_ui.recycler.ListItem

class EmptyCell : ListItem()

fun emptyDelegateAdapter() =
    adapterDelegateViewBinding<EmptyCell, ListItem, ItemEmptyBinding>(
        { layoutInflater, root ->
            ItemEmptyBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

    }