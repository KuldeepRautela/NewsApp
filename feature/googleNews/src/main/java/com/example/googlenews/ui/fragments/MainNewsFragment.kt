package com.example.googlenews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.googlenews.R
import com.example.googlenews.jetpack.models.NewsDto.ResponseState
import com.example.googlenews.jetpack.viewmodels.GoogleNewsViewModel
import com.example.googlenews.utils.GoogleNewsComponent
import kotlinx.coroutines.flow.collect

class MainNewsFragment : Fragment(R.layout.fragment_main_news) {
    override fun onCreate(savedInstanceState: Bundle?) {
        GoogleNewsComponent.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    private val googleNewsViewModel by viewModels<GoogleNewsViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            googleNewsViewModel.getNews().collect {
                when (it) {
                    is ResponseState.Loading -> Log.e("Loading", "data from server")
                    is ResponseState.Success -> Log.e("data", "${it.data}")
                    is ResponseState.Error -> Log.e("error", "${it.errorMsg}")
                    else -> Log.e("something", "else")
                }
            }
        }
    }


}