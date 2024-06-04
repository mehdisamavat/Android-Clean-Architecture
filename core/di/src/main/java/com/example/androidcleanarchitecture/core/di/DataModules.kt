package com.example.androidcleanarchitecture.core.di

import com.example.androidcleanarchitecture.core.network.networkModule
import com.example.androidcleanarchitecture.data.product.di.productDataModule
import org.koin.dsl.module

val dataModules = module {
    includes(
        productDataModule, networkModule
    )

}


