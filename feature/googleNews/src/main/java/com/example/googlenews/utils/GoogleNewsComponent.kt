package com.example.googlenews.utils

import androidx.lifecycle.ViewModel
import com.example.googlenews.jetpack.viewmodels.GoogleNewsViewModel
import com.example.googlenews.ui.fragments.MainNewsFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoSet

@Component(modules = [GoogleNewsComponent.ViewModelModule::class])
interface GoogleNewsComponent {
    companion object {
        fun getInstance(): GoogleNewsComponent = DaggerGoogleNewsComponent.builder().build()
    }

    fun inject(mainNewsFragment: MainNewsFragment)

    @Module
    abstract class ViewModelModule {
        @Binds
        @IntoSet
        @ClassKey(GoogleNewsViewModel::class)
        abstract fun getGoogleNewsViewModel(googleNewsViewModel: GoogleNewsViewModel): ViewModel
    }
}


