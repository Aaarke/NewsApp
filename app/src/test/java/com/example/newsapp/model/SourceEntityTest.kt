package com.example.newsapp.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SourceEntityTest {
    @Mock
    internal var sourceEntityTest: Source? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(sourceEntityTest?.id)
            .thenReturn("cnn")
        Mockito.`when`(sourceEntityTest?.name)
            .thenReturn("CNN")
    }

    @Test
    fun idTest() {
        assertThat<String>(sourceEntityTest?.id, `is`<String>("cnn"))

    }

    @Test
    fun nameTest() {
        assertThat<String>(sourceEntityTest?.name, `is`<String>("CNN"))
    }
}