package com.example.googlenews.utils

import androidx.lifecycle.ViewModel
import com.example.googlenews.di.modules.GoogleNewsModule
import com.example.googlenews.jetpack.viewmodels.GoogleNewsViewModel
import com.example.googlenews.ui.fragments.MainNewsFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Singleton
@Component(modules = [GoogleNewsComponent.ViewModelModule::class,GoogleNewsModule::class])
interface GoogleNewsComponent {
    companion object {
        fun getInstance(): GoogleNewsComponent = DaggerGoogleNewsComponent.builder().build()
    }
    fun getMap():Map<Class<*>,ViewModel>

    fun inject(mainNewsFragment: MainNewsFragment)

    @Module
    abstract class ViewModelModule {
        @Binds
        @IntoMap
        @ClassKey(GoogleNewsViewModel::class)
        abstract fun getGoogleNewsViewModel(googleNewsViewModel: GoogleNewsViewModel): ViewModel
    }
}


