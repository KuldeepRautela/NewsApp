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

@Module
object GoogleNewsModule {

    @Provides
    fun provideOkHttpClient():OkHttpClient
    = OkHttpClient.Builder()
        .readTimeout(2,TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideRetrofitInstance(okHttpClient: OkHttpClient):Retrofit
    = Retrofit.Builder()
        .baseUrl("")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideGoogleNewsApiInstance(retrofitBuilder: Retrofit):ApiService
    = retrofitBuilder.create(ApiService::class.java)

    @Provides
    fun provideGoogleNewsRepositoryInstance(apiService: ApiService):GoogleNewsRepository
    = GoogleNewsImpl(apiService = apiService)

}