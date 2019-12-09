package com.example.newsapp.model

import com.example.newsapp.utility.Constant
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class Article : Serializable {

    @SerializedName("source")
    @Expose
    val source: Source? = null
    @SerializedName("author")
    @Expose
    val author: String? = null
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("description")
    @Expose
    val description: String? = null
    @SerializedName("url")
    @Expose
    val url: String? = null
    @SerializedName("urlToImage")
    @Expose
    val urlToImage: String? = null
    @SerializedName("publishedAt")
    @Expose
    val publishedAt: String? = null
    @SerializedName("content")
    @Expose
    val content: String? = null

    fun getPublishedAtDate(): String {
        val currentFormat = Constant.DateFormat.STANDARD_DATE_FORMAT_TZ
        val desiredDateFormat = Constant.DateFormat.END_USER_DATE_FORMAT3
        val format1 = SimpleDateFormat(currentFormat, Locale.ENGLISH)
        val format2 = SimpleDateFormat(desiredDateFormat, Locale.ENGLISH)
        format1.timeZone = TimeZone.getTimeZone("UTC")
        format2.timeZone = TimeZone.getTimeZone("UTC")
        format1.timeZone = TimeZone.getDefault()
        format2.timeZone = TimeZone.getDefault()
        val date = format1.parse(publishedAt)
        return format2.format(date)
    }
}