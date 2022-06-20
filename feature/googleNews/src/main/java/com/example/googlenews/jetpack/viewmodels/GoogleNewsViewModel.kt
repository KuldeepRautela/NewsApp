package com.example.googlenews.jetpack.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.jetpack.models.NewsDto.ResponseState
import com.example.googlenews.jetpack.repositories.GoogleNewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class GoogleNewsViewModel @Inject constructor(private val googleNewsRepository: GoogleNewsRepository) :
    ViewModel() {
    private val flow = MutableStateFlow<ResponseState<NewsResponse>>(ResponseState.Loading())
    suspend fun getNews() {
        viewModelScope.launch {
            val data = googleNewsRepository.getNewsFromServer()
            flow.emit(data)
        }
    }

    fun newsResponse() = flow as MutableStateFlow
}