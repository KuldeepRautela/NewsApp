package com.example.googlenews.di.modules

import com.example.googlenews.jetpack.repositories.GoogleNewsImpl
import com.example.googlenews.jetpack.repositories.GoogleNewsRepository
import com.example.googlenews.utils.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object GoogleNewsModule {
    @Singleton
    @Provides
    fun provideOkHttpClient():OkHttpClient
    = OkHttpClient.Builder()
        .readTimeout(2,TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient):Retrofit
    = Retrofit.Builder()
        .baseUrl("https://newsdata.io/api/1/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Singleton
    @Provides
    fun provideGoogleNewsApiInstance(retrofitBuilder: Retrofit):ApiService
    = retrofitBuilder.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideGoogleNewsRepositoryInstance(apiService: ApiService):GoogleNewsRepository
    = GoogleNewsImpl(apiService = apiService)

}