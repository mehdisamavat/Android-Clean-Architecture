package com.example.androidcleanarchitecture.domain.product.di

import com.example.androidcleanarchitecture.domain.product.GetProductsUseCase
import com.example.androidcleanarchitecture.domain.product.GetProductsUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val ProductDomainModule = module {
    singleOf(::GetProductsUseCaseImpl) { bind<GetProductsUseCase>() }
}