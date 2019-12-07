package com.example.newsapp.model

import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class NewsDataEntityTest {
    @Mock
    internal var newsDataEntityTest: NewsData? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(newsDataEntityTest?.status)
            .thenReturn("ok")
        Mockito.`when`(newsDataEntityTest?.totalResults)
            .thenReturn(38)
    }

    @Test
    fun statusTest() {
        Assert.assertEquals(newsDataEntityTest?.totalResults, 38)
    }

    @Test
    fun totalResultTest() {
        Assert.assertThat<String>(
            newsDataEntityTest?.status,
            CoreMatchers.`is`<String>("ok")
        )
    }
}