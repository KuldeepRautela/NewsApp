package com.example.googlenews.jetpack.repositories

import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.jetpack.models.NewsDto.ResponseState
import com.example.googlenews.utils.ApiService
import javax.inject.Inject

class GoogleNewsImpl @Inject constructor(private val apiService: ApiService):GoogleNewsRepository {
    override suspend fun getNewsFromServer(): ResponseState<NewsResponse> =
    try{
        ResponseState.Success(apiService.getNews())
    }catch(exception:Exception){
        ResponseState.Error(exception.localizedMessage)
    }
}