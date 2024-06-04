package com.example.androidcleanarchitecture.data.model

import com.google.gson.annotations.SerializedName


enum class Category(val value: String?) {
    @SerializedName("electronics")
    Electronics("electronics"),

    @SerializedName("jewelery")
    Jewelery("jewelery"),

    @SerializedName("men's clothing")
    MenSClothing("men's clothing"),

    @SerializedName("women's clothing")
    WomenSClothing("women's clothing");
}