package com.example.androidcleanarchitecture.data.product.remote

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.core.network.ApiService
import com.example.androidcleanarchitecture.core.network.handler.onSuccess
import com.example.androidcleanarchitecture.data.model.ProductDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ProductRemoteSourceImpl(private val apiService: ApiService) : ProductRemoteSource {
    override suspend fun getProduct(): Flow<NetworkResult<List<ProductDTO>>> {
        return flow {
            val call = apiService.getProducts().onSuccess { }
            emit(call)
        }
    }
}

