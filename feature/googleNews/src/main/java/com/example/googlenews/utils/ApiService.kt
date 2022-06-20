package com.example.googlenews.utils

import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.utils.Constants.apiKey
import retrofit2.http.GET

interface ApiService {
    @GET("everything?q=tesla&from=2022-05-14&sortBy=publishedAt&apiKey=$apiKey")
    suspend fun getNews(): NewsResponse
}