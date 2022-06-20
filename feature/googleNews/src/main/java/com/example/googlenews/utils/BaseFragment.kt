package com.example.googlenews.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding>(private val layoutRes: Int) : Fragment() {

    private var _binding: B? = null
    
    fun requestBinding(): B = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DataBindingUtil.inflate<B>(inflater, layoutRes, container, false)
            .apply { _binding = this }.root
    }
}