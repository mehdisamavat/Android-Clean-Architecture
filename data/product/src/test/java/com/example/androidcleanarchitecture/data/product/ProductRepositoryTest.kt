package com.example.androidcleanarchitecture.data.product


import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.data.model.Category
import com.example.androidcleanarchitecture.data.model.ProductDTO
import com.example.androidcleanarchitecture.data.model.Rating
import com.example.androidcleanarchitecture.data.product.remote.ProductRemoteSource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ProductRepositoryTest {

    private lateinit var productRemoteSource: ProductRemoteSource
    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        productRemoteSource = mock()
        productRepository = ProductRepositoryImpl(productRemoteSource)
    }

    @Test
    fun `getProduct delegates call to productRemoteSource`() = runBlocking {
        val mockData = listOf(
            ProductDTO(
                1, "Product Name", 2.22, "des", Category.Jewelery, "",
                Rating(2.0, 2L)
            )
        )
        val successResult = NetworkResult.Success(mockData)

        whenever(productRemoteSource.getProduct()).thenReturn(flow { emit(successResult) })

        val results = productRepository.getProduct().toList()

        assertEquals(1, results.size)
        assertEquals(successResult, results.first())
    }
}