package com.example.googlenews.jetpack.repositories

import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.jetpack.models.NewsDto.ResponseState

interface GoogleNewsRepository {
    suspend fun getNewsFromServer() : ResponseState<NewsResponse>
}