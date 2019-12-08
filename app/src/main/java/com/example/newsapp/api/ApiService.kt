package com.example.newsapp.api

import com.example.newsapp.model.NewsData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ApiService {
    @GET("services/rest/")
    fun getAllNewsHeadline(@QueryMap map: HashMap<String, String>?): Single<NewsData>
}