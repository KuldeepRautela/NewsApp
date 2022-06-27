package com.example.googlenews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.googlenews.databinding.NewsItemBinding
import com.example.googlenews.jetpack.models.NewsDto.Result
import com.example.googlenews.jetpack.viewmodels.NewsItemViewModel

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ItemViewHolder>() {
    private val newsList = arrayListOf<Result>()

    class ItemViewHolder(private val _binding: NewsItemBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        private val newsItemViewModel = NewsItemViewModel()
        fun bind(Result: Result) {
             newsItemViewModel.init(Result)
            _binding.vm = newsItemViewModel
            _binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
          NewsItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )

    fun submitList(newList : List<Result>){
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

    class DiffCallback(private val oldList: List<Result>, private val newList: List<Result>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItem: Int, newItem: Int) =
            oldList[oldItem].title == newList[newItem].title

        override fun areContentsTheSame(oldItem: Int, newItem: Int) =
            oldList[oldItem] == newList[newItem]
    }
}
