package com.example.newsapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.api.RestClient
import com.example.newsapp.di.DaggerApiComponent
import com.example.newsapp.model.NewsData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsViewModel : ViewModel() {
    var allNewsHeadline = MutableLiveData<NewsData>()
    var allNewsHeadlineLoadError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    var restClient: RestClient? = null
        @Inject set

    init {
        DaggerApiComponent.create().inject(this)
    }

    /**
     * *********************************** Function to fetch news headline data ********************
     * */
    fun fetchAllNewsHeadline() {
        loading.value = true
        compositeDisposable.add(
            restClient?.getAllNewsHeadline()?.subscribeOn(Schedulers.newThread())
                ?.observeOn(AndroidSchedulers.mainThread())
            !!.subscribeWith(object : DisposableSingleObserver<NewsData>() {
                override fun onSuccess(newsData: NewsData) {
                    this@NewsViewModel.allNewsHeadline.value = newsData
                    allNewsHeadlineLoadError.value = false
                    loading.value = false
                }

                override fun onError(e: Throwable) {
                    allNewsHeadlineLoadError.value = true
                    loading.value = false
                    e.printStackTrace()
                }
            })


        )
    }




}
