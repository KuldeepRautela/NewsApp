package com.example.googlenews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.googlenews.R
import com.example.googlenews.databinding.NewsItemBinding
import com.example.googlenews.jetpack.models.NewsDto.Article
import com.example.googlenews.jetpack.models.NewsDto.NewsResponse
import com.example.googlenews.jetpack.viewmodels.NewsItemViewModel

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {
    private val newsList = arrayListOf<Article>()

    class ItemViewHolder(private val _binding: NewsItemBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val newsItemViewModel = NewsItemViewModel()
        fun bind(article: Article) {
            newsItemViewModel.init(article)
            _binding.vm = newsItemViewModel
            _binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.news_item,
                parent,
                false
            )
        )

    fun submitList(newList : List<Article>){
        val diffCallback = DiffCallback(oldList = newsList, newList)
        val diffCourses = DiffUtil.calculateDiff(diffCallback)
        newsList.clear()
        newsList.addAll(newList)
        diffCourses.dispatchUpdatesTo(this)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

    class DiffCallback(private val oldList: List<Article>, private val newList: List<Article>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItem: Int, newItem: Int) =
            oldList[oldItem].title == newList[newItem].title

        override fun areContentsTheSame(oldItem: Int, newItem: Int) =
            oldList[oldItem] == newList[newItem]
    }
}
