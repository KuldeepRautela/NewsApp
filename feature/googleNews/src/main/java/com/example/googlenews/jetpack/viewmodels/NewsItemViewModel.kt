package com.example.googlenews.jetpack.viewmodels
import com.example.googlenews.jetpack.models.NewsDto.Result

class NewsItemViewModel {
    var newsTitle: String? = null
    var newsDescription: String? = null
    var thumbnailUrl: String? = null
    fun init(article: Result) {
        newsTitle = article.title
        newsDescription = article.description
        thumbnailUrl = article.image_url
    }
}