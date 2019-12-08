package com.example.newsapp.di

import com.example.newsapp.api.RestClient
import com.example.newsapp.ui.main.NewsViewModel
import dagger.Component

/**
 * ************ interface acts as a bridge between consumer and provider ***************************
 * */
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(restClient: RestClient)
    fun inject(newsViewModel: NewsViewModel)

}