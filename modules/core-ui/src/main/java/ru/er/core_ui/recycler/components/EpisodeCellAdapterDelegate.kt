package ru.er.core_ui.recycler.components

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.er.core_ui.databinding.ItemSeriesCellBinding
import ru.er.core_ui.recycler.ListItem

data class EpisodeCellElement(
    val title: String,
    val description: String,
    val name: String,
    val date: String,
) : ListItem()

fun episodeCellDelegateAdapter() =
    adapterDelegateViewBinding<EpisodeCellElement, ListItem, ItemSeriesCellBinding>(
        { layoutInflater, root ->
            ItemSeriesCellBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

        bind {
            binding.title.text = "Сезон: ${item.title}"
            binding.description.text = "Серия: ${item.description}"
            binding.name.text = "\"${item.name}\""
            binding.date.text = "Дата выхода: ${item.date}"
        }
    }

