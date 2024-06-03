package com.example.androidcleanarchitecture.data.product

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.data.model.ProductDTO
import com.example.androidcleanarchitecture.data.product.remote.ProductRemoteSource
import kotlinx.coroutines.flow.Flow


internal class ProductRepositoryImpl(private val productRemoteSource: ProductRemoteSource) :
    ProductRepository {
    override suspend fun getProduct(): Flow<NetworkResult<List<ProductDTO>>> {
        return productRemoteSource.getProduct()
    }
}