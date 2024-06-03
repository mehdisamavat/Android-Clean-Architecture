package com.example.androidcleanarchitecture.data.product.di

import com.example.androidcleanarchitecture.data.product.ProductRepository
import com.example.androidcleanarchitecture.data.product.ProductRepositoryImpl
import com.example.androidcleanarchitecture.data.product.remote.ProductRemoteSource
import com.example.androidcleanarchitecture.data.product.remote.ProductRemoteSourceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val productDataModule = module {
    singleOf(::ProductRemoteSourceImpl) { bind<ProductRemoteSource>() }
    singleOf(::ProductRepositoryImpl) { bind<ProductRepository>() }
}