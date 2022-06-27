package com.example.googlenews.utils

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtil {
    @JvmStatic
    @BindingAdapter(value = ["bind_urlToThumbnail"],requireAll = true)
    fun bindThumbnailFromUrl(view: ImageView, url:String?){
        url?.let {
            Glide.with(view).load(Uri.parse(it)).into(view)
        }
    }
}