package com.example.androidcleanarchitecture.core.di

import com.example.androidcleanarchitecture.presentatino.products.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModules = module {
    viewModelOf(::ProductsViewModel)
}
