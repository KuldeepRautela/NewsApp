package com.example.googlenews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.googlenews.R
import com.example.googlenews.databinding.FragmentMainNewsBinding
import com.example.googlenews.jetpack.models.NewsDto.ResponseState
import com.example.googlenews.jetpack.viewmodels.GoogleNewsViewModel
import com.example.googlenews.ui.adapters.NewsAdapter
import com.example.googlenews.utils.BaseFragment
import com.example.googlenews.utils.GoogleNewsComponent
import com.example.googlenews.utils.NewsViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainNewsFragment : BaseFragment<FragmentMainNewsBinding>(R.layout.fragment_main_news) {
    override fun onCreate(savedInstanceState: Bundle?) {
        GoogleNewsComponent.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    private lateinit var googleNewsViewModel: GoogleNewsViewModel

    private val adapter by lazy { NewsAdapter() }

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        googleNewsViewModel =
            ViewModelProvider(this, viewModelFactory).get(GoogleNewsViewModel::class.java)
        requestBinding().apply {
            initUi()
        }
        getNewsData()
    }

    private fun FragmentMainNewsBinding.initUi(){
        newsRv.adapter = adapter
    }

    private fun getNewsData() {
        CoroutineScope(Dispatchers.IO).launch {
            googleNewsViewModel.getNews()
            googleNewsViewModel.newsResponse().collect {
                when (it) {
                    is ResponseState.Loading -> Log.e("Loading", "data from server")
                    is ResponseState.Success -> {
                        Log.e("data", "${it.data}")
                        it.data?.articles?.let { it1 -> adapter.submitList(it1) }
                    }
                    is ResponseState.Error -> Log.e("error", "${it.errorMsg}")
                }
            }
        }
    }


}