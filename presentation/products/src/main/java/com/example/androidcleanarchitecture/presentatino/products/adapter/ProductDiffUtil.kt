package com.example.androidcleanarchitecture.presentatino.products.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.androidcleanarchitecture.model.Product


class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {

    override fun areContentsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: Product,
        newItem: Product
    ): Boolean {
        return oldItem.id == newItem.id
    }
}

