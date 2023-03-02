package ru.milka.episodes.ui.main.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import ru.er.core_ui.recycler.ListItem
import ru.milka.episodes.databinding.ItemPreviewEpisodeBinding
import ru.milka.episodes.models.PreviewEpisodeElement

fun previewEpisodeDelegateAdapter(
    rootPreviewClick: (Int) -> Unit
) =
    adapterDelegateViewBinding<PreviewEpisodeElement, ListItem, ItemPreviewEpisodeBinding>(
        { layoutInflater, root ->
            ItemPreviewEpisodeBinding.inflate(
                layoutInflater,
                root,
                false
            )
        }
    ) {

        bind {
            binding.textTitle.text = item.title
            Picasso.get().load(item.imgPreviewLink).into(binding.imagePreview)
            binding.followSwitcher.isChecked = false
            binding.root.setOnClickListener {
                rootPreviewClick(item.kinopoiskId)
            }
        }
    }