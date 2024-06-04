package com.example.androidcleanarchitecture.data.product

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.core.common.dispatcher.AppDispatchers
import com.example.androidcleanarchitecture.data.model.ProductDTO
import com.example.androidcleanarchitecture.data.product.remote.ProductRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.koin.core.qualifier.named
import org.koin.java.KoinJavaComponent.get


internal class ProductRepositoryImpl(private val productRemoteSource: ProductRemoteSource) :
    ProductRepository {
    override suspend fun getProduct(): Flow<NetworkResult<List<ProductDTO>>> {
        return withContext(
            get(
                clazz = CoroutineDispatcher::class.java,
                qualifier = named(AppDispatchers.IO)
            )
        ) {
            productRemoteSource.getProduct()
        }
    }
}