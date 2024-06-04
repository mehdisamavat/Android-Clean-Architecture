package com.example.androidcleanarchitecture.domain.product

import com.example.androidcleanarchitecture.core.common.NetworkResult
import com.example.androidcleanarchitecture.data.model.ProductDTO
import com.example.androidcleanarchitecture.data.product.ProductRepository
import com.example.androidcleanarchitecture.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

internal class GetProductsUseCaseImpl(private val productRepository: ProductRepository) :
    GetProductsUseCase {

    override suspend fun invoke(): Flow<NetworkResult<List<Product>>> {
        return productRepository.getProduct()
            .transform { res ->
                when (res) {
                    is NetworkResult.Error -> {
                        emit(NetworkResult.Error(res.code, res.message))
                    }

                    is NetworkResult.Exception -> {
                        emit(NetworkResult.Exception(res.e))
                    }

                    is NetworkResult.Success -> {
                        emit(NetworkResult.Success(res.data.toDomainMapper()))
                    }
                }
            }
    }

    private fun List<ProductDTO>.toDomainMapper(): List<Product> {
        return map {
            Product(
                id = it.id ?: 0,
                title = it.title.orEmpty(),
                price = it.price ?: 0.0,
                description = it.description.orEmpty(),
                image = it.image.orEmpty(),
                category = it.category?.value.orEmpty(),
                rate = it.rating?.rate ?: 2.0,
                count = it.rating?.count ?: 0
            )
        }
    }

}