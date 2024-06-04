package com.example.androidcleanarchitecture.core.di

import com.example.androidcleanarchitecture.domain.product.di.ProductDomainModule
import org.koin.dsl.module

val domainModules = module {
    includes(
        ProductDomainModule
    )
}