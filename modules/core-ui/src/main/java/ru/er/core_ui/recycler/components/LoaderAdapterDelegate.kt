package ru.er.core_ui.recycler.components

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.er.core_ui.databinding.ItemLoaderBinding
import ru.er.core_ui.recycler.ListItem

class LoaderElement : ListItem()

fun loaderDelegateAdapter() =
    adapterDelegateViewBinding<LoaderElement, ListItem, ItemLoaderBinding>(
        { layoutInflater, root ->
            ItemLoaderBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

    }