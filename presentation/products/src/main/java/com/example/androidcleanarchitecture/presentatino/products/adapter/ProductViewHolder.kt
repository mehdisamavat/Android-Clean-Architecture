package com.example.androidcleanarchitecture.presentatino.products.adapter


import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.androidcleanarchitecture.core.image.loadImage
import com.example.androidcleanarchitecture.model.Product
import com.example.androidcleanarchitecture.presentation.products.R
import com.example.androidcleanarchitecture.presentation.products.databinding.ItemProductBinding

class ProductViewHolder(private val binding: ItemProductBinding) :
    ViewHolder(binding.root) {
    fun bind(item: Product, onClickProduct: ((description: String) -> Unit)?) {
        with(binding) {
            val resource = root.context.resources
            ivProduct.loadImage(url = item.image, null, null)
            tvTitleProduct.text = item.title
            tvPriceProduct.text = resource.getString(R.string.price, item.price)
            tvCategoryProduct.text = resource.getString(R.string.category, item.category)
            tvRateProduct.text = resource.getString(R.string.rate, item.rate)
            tvCountProduct.text = resource.getString(R.string.count, item.count)
            root.setOnClickListener {
                onClickProduct?.invoke(item.description)
            }
        }

    }

    fun unBind() {
        binding.root.setOnClickListener(null)
    }
}