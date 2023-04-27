package ru.milka.episodes.ui.details.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.squareup.picasso.Picasso
import ru.er.core_ui.recycler.ListItem
import ru.milka.episodes.databinding.ItemHeaderDetailsFilmBinding
import ru.milka.episodes.models.DetailsFilmHeaderElement

fun headerEpisodesDetailsFilmDelegateAdapter(
    kinopoiskClick: (Int) -> Unit,
    switchClick: (Boolean) -> Unit
) = adapterDelegateViewBinding<DetailsFilmHeaderElement, ListItem, ItemHeaderDetailsFilmBinding>(
    { layoutInflater, root ->
        ItemHeaderDetailsFilmBinding.inflate(
            layoutInflater,
            root,
            false
        )
    }
) {

    bind {
        with(binding) {
            Picasso.get().load(item.imgPreviewLink).into(imagePreview)
            title.text = item.title
            rating.text = "Рейинг: ${item.rating}"
            yearStart.text = "Год выпуска: ${item.yearStart}"
            toKinopoiskButton.setOnClickListener { kinopoiskClick(item.kinopoiskId) }
            followSwitcher.setOnClickListener {
            }
        }
    }
}