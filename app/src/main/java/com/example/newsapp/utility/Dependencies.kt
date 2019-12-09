package com.example.newsapp.utility

import android.content.Context
import com.example.newsapp.api.Keys
import com.example.newsapp.api.Keys.Prefs.Companion.LIST_OF_ARTICLES
import com.example.newsapp.model.Article
import com.example.newsapp.model.NewsData
import io.paperdb.Paper
import kotlin.collections.ArrayList

class Dependencies : Keys.Prefs {
    companion object {
        private val TAG = Dependencies::class.java.name
        private var newsData: NewsData? = null

        fun setNews( listOfArticles: NewsData) {
            this.newsData = listOfArticles
            if (this.newsData == null) {
                Paper.book().delete(LIST_OF_ARTICLES)
            } else {
                Paper.book().write(LIST_OF_ARTICLES, this.newsData)
            }
        }

        fun getNews(): NewsData? {
            if (newsData == null)
                newsData = Paper.book()
                    .read(LIST_OF_ARTICLES, NewsData())
            return newsData
        }


    }


}