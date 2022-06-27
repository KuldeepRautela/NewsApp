package com.example.googlenews.jetpack.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.jetpack.models.NewsDto.ResponseState
import com.example.googlenews.jetpack.models.NewsDto.Result
import com.example.googlenews.jetpack.repositories.GoogleNewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class GoogleNewsViewModel @Inject constructor(private val googleNewsRepository: GoogleNewsRepository) :
    ViewModel() {
    private val flow = MutableStateFlow<ResponseState<NewsResponse>>(ResponseState.Empty())
    val searchText = MutableLiveData<String>()
    val isLoaderVisible = MutableLiveData<Boolean>()
    val searchResults = MutableLiveData<List<Result>>()
    init {
        isLoaderVisible.value = false
        searchText.observeForever {
         viewModelScope.launch {
             getNews(it)
         }
        }
        viewModelScope.launch {
            flow.collect {
                when(it){
                    is ResponseState.Loading -> {
                        isLoaderVisible.value = true
                        searchResults.value = emptyList()
                    }
                    is ResponseState.Success -> {
                        isLoaderVisible.value = false
                        searchResults.value = it.data?.results
                    }
                    is ResponseState.Error -> {
                        isLoaderVisible.value = false
                        searchResults.value = emptyList()
                        it.errorMsg?.let { it1 -> Log.e("Error Is : ", it1) }
                    }
                    else -> return@collect
                }
            }
        }
    }

    private suspend fun getNews(query:String) {
            flow.value = ResponseState.Loading()
            val data = googleNewsRepository.getNewsFromServer(query)
            flow.emit(data)
    }

}