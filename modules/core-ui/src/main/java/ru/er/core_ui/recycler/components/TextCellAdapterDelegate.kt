package ru.er.core_ui.recycler.components

import androidx.annotation.StringRes
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.er.core_ui.databinding.ItemTextCellBinding
import ru.er.core_ui.recycler.ListItem

data class TextCellElement(
    @StringRes val titleRes: Int? = null,
    val title: String? = null,
    val description: String
) : ListItem()

fun textCellDelegateAdapter() =
    adapterDelegateViewBinding<TextCellElement, ListItem, ItemTextCellBinding>(
        { layoutInflater, root ->
            ItemTextCellBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

        bind {
            item.titleRes.let {
                binding.title.text = if (it != null) item.title else it
            }
            binding.description.text = item.description
        }
    }

