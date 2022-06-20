package com.example.googlenews.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtil {

    @BindingAdapter(value = ["bind_urlToThumbnail"],requireAll = true)
    fun bindThumbnailFromUrl(view: ImageView, url:String){
     Glide.with(view).load(Uri.parse(url)).into(view)
    }
}