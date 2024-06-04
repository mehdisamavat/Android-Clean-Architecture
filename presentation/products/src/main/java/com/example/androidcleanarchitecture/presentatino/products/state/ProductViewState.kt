package com.example.androidcleanarchitecture.presentatino.products.state

import com.example.androidcleanarchitecture.core.common.ViewStatus
import com.example.androidcleanarchitecture.model.Product

data class ProductViewState(
    val products: List<Product> = listOf(),
    val viewStatus: ViewStatus = ViewStatus.INITIAL,

    )