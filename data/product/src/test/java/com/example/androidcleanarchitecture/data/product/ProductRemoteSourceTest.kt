package com.example.androidcleanarchitecture.data.product

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.core.network.ApiService
import com.example.androidcleanarchitecture.data.model.Category
import com.example.androidcleanarchitecture.data.model.ProductDTO
import com.example.androidcleanarchitecture.data.model.Rating
import com.example.androidcleanarchitecture.data.product.remote.ProductRemoteSource
import com.example.androidcleanarchitecture.data.product.remote.ProductRemoteSourceImpl
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ProductRemoteSourceTest {

    private lateinit var apiService: ApiService
    private lateinit var productRemoteSource: ProductRemoteSource

    @Before
    fun setUp() {
        apiService = mock()
        productRemoteSource = ProductRemoteSourceImpl(apiService)
    }

    @Test
    fun `getProduct returns success result when api call succeeds`() = runBlocking {
        val mockData = listOf(
            ProductDTO(
                1, "Product Name", 2.22, "des", Category.Jewelery, "",
                Rating(2.0, 2L)
            )
        )
        val successResult = NetworkResult.Success(mockData)

        whenever(apiService.getProducts()).thenReturn(successResult)

        val results = productRemoteSource.getProduct().toList()

        assertEquals(1, results.size)
        assertEquals(successResult, results.first())
    }
}