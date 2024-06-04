package com.example.androidcleanarchitecture.presentatino.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.androidcleanarchitecture.model.Product
import com.example.androidcleanarchitecture.presentation.products.databinding.ItemProductBinding


class ProductAdapter(private val onClickProduct: ((description: String) -> Unit)?) :
    ListAdapter<Product, ProductViewHolder>(ProductDiffUtil()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {
        return ProductViewHolder(
            ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        holder.bind(currentList[position], onClickProduct)
    }

    override fun onViewRecycled(holder: ProductViewHolder) {
        super.onViewRecycled(holder)
        holder.unBind()
    }

}

