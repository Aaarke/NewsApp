package com.example.newsapp.model

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class Source : Serializable {
    @SerializedName("id")
    @Expose
    val id: Any? = null
    @SerializedName("name")
    @Expose
    val name: String? = null
}