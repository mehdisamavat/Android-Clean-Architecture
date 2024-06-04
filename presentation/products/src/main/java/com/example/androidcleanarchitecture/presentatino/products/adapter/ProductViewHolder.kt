package com.example.androidcleanarchitecture.presentatino.products.adapter


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidcleanarchitecture.core.image.loadImage
import com.example.androidcleanarchitecture.model.Product
import com.example.androidcleanarchitecture.presentation.products.databinding.ItemProductBinding

class ProductViewHolder(val binding: ItemProductBinding) :
    ViewHolder(binding.root) {
    fun bind(item: Product) {

        binding.ivProduct.loadImage(
            url = item.image, null, null
        )

        binding.title.text = item.title
    }
}