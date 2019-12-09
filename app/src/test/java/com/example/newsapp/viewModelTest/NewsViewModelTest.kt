package com.example.newsapp.viewModelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.newsapp.api.RestClient
import com.example.newsapp.model.NewsData
import com.example.newsapp.ui.main.NewsViewModel
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class NewsViewModelTest {
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()
    @Mock
    internal var restClient: RestClient? = null

    private var testSingle: Single<NewsData>? = null

    @InjectMocks
    internal var newsViewModel = NewsViewModel()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun allNewsHeadlineSuccessTest() {
        val newsData = NewsData()
        testSingle = Single.just(newsData)
        `when`(restClient?.getAllNewsHeadline()).thenReturn(testSingle)
        newsViewModel.fetchAllNewsHeadline()
        Assert.assertEquals(newsData, newsViewModel.allNewsHeadline.value)
        Assert.assertEquals(false, newsViewModel.allNewsHeadlineLoadError.value)
        Assert.assertEquals(false, newsViewModel.loading.value)
    }


    /*
  * **************Test case of null****************************************************************
  * */
    @Test
    fun testNull() {
        `when`(restClient?.getAllNewsHeadline()).thenReturn(null)
        Assert.assertNull(newsViewModel.allNewsHeadline.value)
    }


    @Test
    fun allNewsHeadlineFailureTest() {
        val newsData = NewsData()
        testSingle = Single.just(newsData)
        `when`(restClient?.getAllNewsHeadline()).thenReturn(Single.error<NewsData>(Throwable("Api error")))
        newsViewModel.fetchAllNewsHeadline()
        Assert.assertEquals(true, newsViewModel.allNewsHeadlineLoadError.value)
        Assert.assertEquals(false, newsViewModel.loading.value)
    }


    /*
    ************ setting rx schedulers**************************************************************
    * */
    @Before
    fun setupRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, true)
            }
        }

        RxJavaPlugins.setInitNewThreadSchedulerHandler { immediate }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { immediate }
    }
}