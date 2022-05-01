package com.example.googlenews.jetpack.viewmodels

import androidx.lifecycle.ViewModel
import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.jetpack.models.NewsDto.ResponseState
import com.example.googlenews.jetpack.repositories.GoogleNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class GoogleNewsViewModel @Inject constructor(private val googleNewsRepository: GoogleNewsRepository) : ViewModel() {
 suspend fun getNews() = flow<ResponseState<NewsResponse>> {
     emit(ResponseState.Loading())
     emit(googleNewsRepository.getNewsFromServer())
 }
}