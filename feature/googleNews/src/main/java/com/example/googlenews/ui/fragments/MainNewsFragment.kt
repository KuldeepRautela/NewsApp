package com.example.googlenews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.SearchView
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
import kotlinx.coroutines.MainScope
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
            vm = googleNewsViewModel
            lifecycleOwner = viewLifecycleOwner
            initUi()
        }
        getNewsData()
    }

    private fun FragmentMainNewsBinding.initUi(){
        newsRv.adapter = adapter
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                googleNewsViewModel.searchText.value = query
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }

        })
    }

    private fun getNewsData() {
        CoroutineScope(Dispatchers.IO).launch {
            googleNewsViewModel.newsResponse().collect {
                when (it) {
                    is ResponseState.Loading -> Log.e("Loading", "data from server")
                    is ResponseState.Success -> {
                        MainScope().launch {
                            it.data?.results?.let { it1 -> adapter.submitList(it1) }
                        }
                    }
                    is ResponseState.Error -> Log.e("error", "${it.errorMsg}")
                }
            }
        }
    }
}