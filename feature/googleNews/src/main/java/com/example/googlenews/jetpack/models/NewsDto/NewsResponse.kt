package com.example.googlenews.jetpack.models.NewsDto

data class NewsResponse(
    val nextPage: Int,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)