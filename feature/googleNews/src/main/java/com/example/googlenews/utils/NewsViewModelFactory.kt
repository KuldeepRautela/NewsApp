package com.example.googlenews.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.googlenews.jetpack.repositories.GoogleNewsRepository
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(private val map:Map<Class<*>,@JvmSuppressWildcards ViewModel>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return map[modelClass] as T
    }
}