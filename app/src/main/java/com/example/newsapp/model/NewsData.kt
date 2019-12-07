package com.example.newsapp.model

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class NewsData : Serializable {
    @SerializedName("status")
    @Expose
    val status: String? = null
    @SerializedName("totalResults")
    @Expose
    val totalResults: Int? = null
    @SerializedName("articles")
    @Expose
    val articles: List<Article>? = null
}