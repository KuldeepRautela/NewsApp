package com.example.googlenews.jetpack.viewmodels

import com.example.googlenews.jetpack.models.NewsDto.Article

class NewsItemViewModel {
    var newsTitle: String? = null
    var newsDescription: String? = null
    var thumbnailUrl: String? = null
    fun init(article: Article) {
        newsTitle = article.title
        newsDescription = article.description
        thumbnailUrl = article.urlToImage
    }
}