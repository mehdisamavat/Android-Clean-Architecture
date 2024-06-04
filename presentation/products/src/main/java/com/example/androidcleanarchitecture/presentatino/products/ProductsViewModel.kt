package com.example.androidcleanarchitecture.presentatino.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.core.common.ViewStatus
import com.example.androidcleanarchitecture.domain.product.GetProductsUseCase
import com.example.androidcleanarchitecture.model.Product
import com.example.androidcleanarchitecture.presentatino.products.state.ProductViewState
import com.example.androidcleanarchitecture.presentatino.products.state.SearchProductViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsViewModel(private val getProductsUseCase: GetProductsUseCase) : ViewModel() {

    private val _allProductsStateFlow = MutableStateFlow(ProductViewState())

    private val _productsStateFlow = MutableStateFlow(SearchProductViewState())
    val productsStateFlow = _productsStateFlow.asStateFlow()

    val queryStateFlow = MutableStateFlow("")

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase().onStart {
                _productsStateFlow.update {
                    it.copy(viewStatus = ViewStatus.LOADING)
                }
            }.collect { response ->
                when (response) {
                    is NetworkResult.Success -> {
                        _allProductsStateFlow.update {
                            it.copy(
                                products = response.data.toMutableList(),
                                viewStatus = ViewStatus.SUCCESS
                            )
                        }
                        searchQueryStateFlowHandler()
                    }

                    is NetworkResult.Error -> {
                        _productsStateFlow.update {
                            it.copy(
                                viewStatus = ViewStatus.FAILED,
                                message = response.message.orEmpty()
                            )
                        }
                    }

                    is NetworkResult.Exception -> {
                        _productsStateFlow.update {
                            it.copy(
                                viewStatus = ViewStatus.FAILED,
                                message = response.e.message.orEmpty()
                            )
                        }
                    }
                }

            }
        }
    }

    private fun searchQueryStateFlowHandler() {
        viewModelScope.launch(Dispatchers.Default) {
            queryStateFlow
                .debounce(200)
                .filter { query ->
                    if (query.isBlank()) {
                        _productsStateFlow.update {
                            it.copy(
                                products = _allProductsStateFlow.value.products,
                                viewStatus = ViewStatus.SUCCESS
                            )
                        }
                        return@filter false
                    } else {
                        _productsStateFlow.update { it.copy(viewStatus = ViewStatus.LOADING) }
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    searchProducts()
                }.collect { products ->
                    _productsStateFlow.update {
                        it.copy(products = products, viewStatus = ViewStatus.SUCCESS)
                    }
                }
        }
    }

    private fun searchProducts(): Flow<List<Product>> {
        return _allProductsStateFlow.combine(queryStateFlow) { products, query ->
            products.products.filter { product ->
                product.title.lowercase().contains(query.lowercase())
            }
        }
    }

}