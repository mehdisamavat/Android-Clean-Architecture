package com.example.androidcleanarchitecture.core.network

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.data.model.ProductDTO
import retrofit2.http.GET

interface ApiService {
    @GET(value = "products")
    suspend fun getProducts(): NetworkResult<List<ProductDTO>>
}
