package com.app.tribewac.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.tribewac.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)

    @BindingAdapter("imageUrlImage")
    fun loadImages(view: ImageView, url: String?) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .timeout(60000)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .error(R.drawable.placeholder)
            .into(view)
    }

}