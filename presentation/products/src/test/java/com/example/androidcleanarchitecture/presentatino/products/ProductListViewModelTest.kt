package com.example.androidcleanarchitecture.presentatino.products

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.domain.product.GetProductsUseCase
import com.example.androidcleanarchitecture.model.Product
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ProductListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getProductsUseCase: GetProductsUseCase

    private lateinit var viewModel: ProductsViewModel

    @Before
    fun setUp() {
        viewModel = ProductsViewModel(getProductsUseCase)
    }

    @Test
    fun `fetchProducts fetches data from use case and updates state`() = runBlocking {
        val mockData = listOf(Product(1, "Product Name", 0.0, "", "", 0.0, 0))
        val mockSuccessResult = NetworkResult.Success(mockData)

        coEvery { getProductsUseCase.invoke() } returns flow { emit(mockSuccessResult) }

        viewModel.getProducts()

        val results = viewModel.productsStateFlow.value.products.toList()

        assertEquals(1, results.size)
        assertEquals(mockData, results.first())
    }

    @Test
    fun `fetchProducts shows loading and error states on errors`() = runBlocking {
        val errorCode = 400
        val errorMessage = "API error"
        val mockErrorResult = NetworkResult.Error(errorCode, errorMessage)

        coEvery { getProductsUseCase.invoke() } returns flow { emit(mockErrorResult) }

        viewModel.getProducts()

        // Verify state changes
        verify { viewModel._isLoading.postValue(true) }
        verify { viewModel._isLoading.postValue(false) }
        verify { viewModel._errorMessage.postValue(errorMessage) }

        // Assert no data is emitted
        assertEquals(0, viewModel.productList.toList().size)
    }
}
