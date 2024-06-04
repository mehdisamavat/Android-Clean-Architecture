package com.example.androidcleanarchitecture.model

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val image: String,
    val rate: Double,
    val count: Long
)
