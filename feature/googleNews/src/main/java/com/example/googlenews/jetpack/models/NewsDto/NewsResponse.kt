package com.example.googlenews.jetpack.models.NewsDto

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)