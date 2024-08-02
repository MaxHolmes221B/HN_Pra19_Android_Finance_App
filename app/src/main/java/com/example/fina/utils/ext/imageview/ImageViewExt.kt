package com.example.fina.utils.ext.imageview

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.engine.GlideException
import android.graphics.drawable.PictureDrawable

fun ImageView.loadImageCircleWithUrl(url: String, placeholderResId: Int) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions().circleCrop().placeholder(placeholderResId).error(placeholderResId))
        .into(this)
}


fun ImageView.loadImageWithUrl(url: String) {
    Glide.with(this)
        .`as`(PictureDrawable::class.java) // Đảm bảo sử dụng kiểu PictureDrawable cho SVG
        .load(url)
        .listener(SvgSoftwareLayerSetter()) // Để xử lý SVG
        .into(this)
}