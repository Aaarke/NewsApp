package com.example.newsapp.api

import com.example.newsapp.di.DaggerApiComponent
import com.example.newsapp.model.NewsData
import io.reactivex.Single
import javax.inject.Inject

class RestClient {
    //will get inject by dagger
    var getApiService: ApiService? = null
        @Inject set

    init {
        initDagger()
    }

    private fun initDagger() {
        DaggerApiComponent.create().inject(this)

    }


    fun getAllNewsHeadline(): Single<NewsData> {
        val map = HashMap<String, String>()
        map[Keys.ApiField.REQ_API_KEY] = Keys.Constant.API_KEY
        return getApiService!!.getAllNewsHeadline(map)

    }

    companion object {
        const val BASE_URL = "https://newsapi.org/v2"
        private var instance: RestClient? = null
        fun getInstance(): RestClient {
            if (instance == null) {
                instance = RestClient()
            }
            return RestClient()
        }
    }
}