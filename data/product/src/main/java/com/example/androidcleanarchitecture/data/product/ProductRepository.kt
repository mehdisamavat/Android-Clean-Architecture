package com.example.androidcleanarchitecture.data.product

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.data.model.ProductDTO
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProduct(): Flow<NetworkResult<List<ProductDTO>>>
}