package com.example.googlenews.utils

import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.utils.Constants.apiKey
import retrofit2.http.GET

interface ApiService {
    @GET("/top-headlines?sources=google-news-in&apiKey=$apiKey")
    suspend fun getNews():NewsResponse
}