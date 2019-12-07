package com.example.newsapp.model

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ArticleEntityTest {
    @Mock
    internal var articleEntityTest: Article? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Mockito.`when`(articleEntityTest?.author)
            .thenReturn("Eliza Mackintosh, Maija-Liisa Ehlinger and Jennifer Hansler, CNN")
        Mockito.`when`(articleEntityTest?.title)
            .thenReturn("Iran releases American student in prisoner swap, foreign minister says - CNN")
        Mockito.`when`(articleEntityTest?.description)
            .thenReturn("An American graduate student, who had been held for three years in Iran on suspicion of being a spy, has been freed and is undergoing a medical evaluation at an American Army hospital in Germany before eventually traveling back to the US.")
        Mockito.`when`(articleEntityTest?.url)
            .thenReturn("https://www.cnn.com/2019/12/07/politics/xiyue-wang-released-iran-prisoner-swap-intl/index.html")
        Mockito.`when`(articleEntityTest?.urlToImage)
            .thenReturn("https://cdn.cnn.com/cnnnext/dam/assets/191207094048-01-xiyue-wang-return-1207-super-tease.jpg")
        Mockito.`when`(articleEntityTest?.publishedAt).thenReturn("2019-12-07T14:10:00Z")
        Mockito.`when`(articleEntityTest?.content)
            .thenReturn("(CNN)An American graduate student, who had been held for three years in Iran on suspicion of being a spy, has been freed and is undergoing a medical evaluation at an American Army hospital in Germany before eventually traveling back to the US.\\r\\nChinese-born X… [+4373 chars]")
    }

    @Test
    fun authorTest() {
        assertThat<String>(
            articleEntityTest?.author,
            `is`<String>("Eliza Mackintosh, Maija-Liisa Ehlinger and Jennifer Hansler, CNN")
        )
    }

    @Test
    fun titleTest() {
        assertThat<String>(
            articleEntityTest?.title,
            `is`<String>("Iran releases American student in prisoner swap, foreign minister says - CNN")
        )

    }

    @Test
    fun descriptionTest() {
        assertThat<String>(
            articleEntityTest?.description,
            `is`<String>("An American graduate student, who had been held for three years in Iran on suspicion of being a spy, has been freed and is undergoing a medical evaluation at an American Army hospital in Germany before eventually traveling back to the US.")
        )

    }

    @Test
    fun urlTest() {
        assertThat<String>(
            articleEntityTest?.url,
            `is`<String>("https://www.cnn.com/2019/12/07/politics/xiyue-wang-released-iran-prisoner-swap-intl/index.html")
        )

    }

    @Test
    fun urlToImageTest() {
        assertThat<String>(
            articleEntityTest?.urlToImage,
            `is`<String>("https://cdn.cnn.com/cnnnext/dam/assets/191207094048-01-xiyue-wang-return-1207-super-tease.jpg")
        )

    }

    @Test
    fun publishedAtTest() {
        assertThat<String>(articleEntityTest?.publishedAt, `is`<String>("2019-12-07T14:10:00Z"))

    }

    @Test
    fun contentTest() {
        assertThat<String>(
            articleEntityTest?.content,
            `is`<String>("(CNN)An American graduate student, who had been held for three years in Iran on suspicion of being a spy, has been freed and is undergoing a medical evaluation at an American Army hospital in Germany before eventually traveling back to the US.\\r\\nChinese-born X… [+4373 chars]")
        )

    }
}