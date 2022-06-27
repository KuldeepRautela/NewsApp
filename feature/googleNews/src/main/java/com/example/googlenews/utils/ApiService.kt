package com.example.googlenews.utils

import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.utils.Constants.apiKey
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("news?apikey=$apiKey")
    suspend fun getNews(@Query("q") query:String): NewsResponse
}