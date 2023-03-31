package com.vb.cats.cats.widget

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.vb.cats.database.entity.Cat

class CatAdapter : ListAdapter<Cat, CatViewHolder>(
    object : DiffUtil.ItemCallback<Cat>() {
        override fun areItemsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Cat, newItem: Cat): Boolean {
            return oldItem.url == newItem.url
        }
    }
) {
    var onFavouriteClickListener: ((Cat) -> Unit)? = null
    var onSaveToGalleryClickListener: ((Cat) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder =
        CatViewHolder(CatItemView(parent.context))

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        with(holder.itemView as CatItemView) {
            setOnFavouriteClickListener {
                onFavouriteClickListener?.invoke(getItem(position))
                changeFavouriteIconState()
            }
            setOnSaveToGalleryClickListener {
                onSaveToGalleryClickListener?.invoke(getItem(position))
            }
            setItem(getItem(position))
        }
    }
}
