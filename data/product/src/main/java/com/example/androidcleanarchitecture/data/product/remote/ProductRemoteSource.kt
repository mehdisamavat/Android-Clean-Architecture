package com.example.androidcleanarchitecture.data.product.remote

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.data.model.ProductDTO
import kotlinx.coroutines.flow.Flow

internal interface ProductRemoteSource {
    suspend fun getProduct(): Flow<NetworkResult<List<ProductDTO>>>
}