package com.example.androidcleanarchitecture.data.model


data class ProductDTO(
    val id: Long?,
    val title: String?,
    val price: Double?,
    val description: String?,
    val category: Category?,
    val image: String?,
    val rating: Rating?
)