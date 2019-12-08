package com.example.newsapp.di

import com.example.newsapp.api.ApiService
import com.example.newsapp.api.RestClient
import dagger.Module
import dagger.Provides
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class ApiModule {
    @Provides
    fun provideAllNewsHeadlineApiService(): ApiService {
        return Retrofit.Builder().baseUrl(RestClient.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient().build())
            .build()
            .create(ApiService::class.java)
    }

    private fun httpClient(): OkHttpClient.Builder {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 2
        httpClient.dispatcher(dispatcher)
        httpClient.addInterceptor(logging)
        httpClient.readTimeout(60.toLong(), TimeUnit.SECONDS)
        httpClient.writeTimeout(60.toLong(), TimeUnit.SECONDS)
        return httpClient
    }


    @Provides
    fun getInstance(): RestClient {
        return RestClient.getInstance()
    }
}