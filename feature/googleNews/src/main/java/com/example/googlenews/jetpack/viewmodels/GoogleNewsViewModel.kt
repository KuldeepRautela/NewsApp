package com.example.googlenews.jetpack.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
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
    val searchText = MutableLiveData<String>()
    init {
        searchText.observeForever {
         viewModelScope.launch {
             getNews(it)
         }
        }
    }
    suspend fun getNews(query:String) {
        viewModelScope.launch {
            val data = googleNewsRepository.getNewsFromServer(query)
            flow.emit(data)
        }
    }

    fun newsResponse() = flow
}