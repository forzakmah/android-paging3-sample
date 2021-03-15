package com.bkapps.news.ui

import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.databinding.BindingAdapter
import com.bkapps.news.utils.loadImage

@BindingAdapter("app:imageUrl")
fun loadImg(img: ImageView, @NonNull url: String) {
    img.loadImage(url)
}