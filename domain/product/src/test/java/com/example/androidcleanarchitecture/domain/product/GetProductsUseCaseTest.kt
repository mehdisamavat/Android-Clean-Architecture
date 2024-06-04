package com.example.androidcleanarchitecture.domain.product


import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.data.model.Category
import com.example.androidcleanarchitecture.data.model.ProductDTO
import com.example.androidcleanarchitecture.data.model.Rating
import com.example.androidcleanarchitecture.data.product.ProductRepository
import com.example.androidcleanarchitecture.model.Product
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetProductsUseCaseTest {

    private lateinit var productRepository: ProductRepository
    private lateinit var getProductsUseCase: GetProductsUseCase

    @Before
    fun setUp() {
        productRepository = mock()
        getProductsUseCase = GetProductsUseCaseImpl(productRepository)
    }

    @Test
    fun `getProduct transforms NetworkResult and maps to Product domain`() = runBlocking {
        val mockDataDto = listOf(
            ProductDTO(
                1, "Product Name", 2.22, "des", Category.Jewelery, "",
                Rating(2.0, 2L)
            )
        )
        val mockSuccessResultDto = NetworkResult.Success(mockDataDto)

        val mockData = listOf(
            Product(1, "Product Name", 2.22, "des", "", 2.0, 2L)
        )
        val expectedSuccessResult = NetworkResult.Success(mockData)

        whenever(productRepository.getProduct()).thenReturn(flow { emit(mockSuccessResultDto) })

        val results = getProductsUseCase.invoke().toList()

        assertEquals(1, results.size)
        assertEquals(expectedSuccessResult, results.first())
    }

    @Test
    fun `getProduct propagates errors from repository`() = runBlocking {
        val errorCode = 400
        val errorMessage = "API error"
        val mockErrorResult: NetworkResult<List<ProductDTO>> =
            NetworkResult.Error(errorCode, errorMessage)

        whenever(productRepository.getProduct()).thenReturn(flow { emit(mockErrorResult) })

        val results = getProductsUseCase.invoke().toList()

        assertEquals(1, results.size)
        assertEquals(mockErrorResult, results.first())
    }

    @Test
    fun `getProduct propagates exceptions from repository`() = runBlocking {
        val mockException = Exception("Network exception")
        val mockExceptionResult: NetworkResult<List<ProductDTO>> =
            NetworkResult.Exception(mockException)

        whenever(productRepository.getProduct()).thenReturn(flow { emit(mockExceptionResult) })

        val results = getProductsUseCase.invoke().toList()

        assertEquals(1, results.size)
        assertEquals(mockExceptionResult, results.first())
    }
}
