package com.vb.cats.cats.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.vb.cats.R
import com.vb.cats.database.entity.Cat
import com.vb.cats.databinding.CatItemViewBinding

class CatItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding =
        CatItemViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        layoutParams = binding.root.layoutParams
    }

    fun setItem(cat: Cat) {
        if (cat.isFavourite == true) {
            binding.catItemAddToFavourites.isClickable = false
            binding.catItemAddToFavourites.setImageResource(R.drawable.ic_baseline_check_24)
        } else {
            binding.catItemAddToFavourites.isClickable = true
            binding.catItemAddToFavourites.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        Glide.with(context)
            .load(cat.url)
            .into(binding.catItemImage)
    }

    fun changeFavouriteIconState() {
        binding.catItemAddToFavourites.isClickable = false
        binding.catItemAddToFavourites.setImageResource(R.drawable.ic_baseline_check_24)
    }

    fun setOnFavouriteClickListener(listener: OnClickListener) {
        binding.catItemAddToFavourites.setOnClickListener(listener)
    }

    fun setOnSaveToGalleryClickListener(listener: OnClickListener) {
        binding.catItemSaveToGallery.setOnClickListener(listener)
    }
}
