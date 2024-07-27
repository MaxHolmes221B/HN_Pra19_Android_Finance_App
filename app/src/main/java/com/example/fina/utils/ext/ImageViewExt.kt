package com.example.fina.utils.ext

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageCircleWithUrl(url: String) {
    Glide.with(this)
        .load(url)
        .circleCrop()
        .into(this)
}

fun ImageView.loadImageWithUrl(url: String) {
    Glide.with(this)
        .load(url)
        .into(this)
}