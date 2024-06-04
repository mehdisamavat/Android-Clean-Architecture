package com.example.androidcleanarchitecture.core.image

import android.widget.ImageView
import coil.load
import coil.request.CachePolicy
import coil.size.Scale
import coil.size.ViewSizeResolver


fun ImageView.loadImage(
    url: String?,
    placeholder: Int?,
    error: Int? = null,
    onRequestStart: (() -> Unit)? = null,
    onRequestSuccess: (() -> Unit)? = null,
    onRequestFail: (() -> Unit)? = null,
) {

    val errorImage = (error ?: placeholder) ?: 0

    load(url) {
        size(ViewSizeResolver(this@loadImage))
        placeholder(drawableResId = placeholder ?: 0)
        placeholderMemoryCacheKey(placeholder?.toString())
        error(drawableResId = errorImage)
        scale(Scale.FIT)
        allowHardware(false)
        networkCachePolicy(CachePolicy.ENABLED)
        listener(
            onStart = {
                onRequestStart?.invoke()
            },
            onSuccess = { _, _ ->
                onRequestSuccess?.invoke()
            },
            onError = { _, _ ->
                onRequestFail?.invoke()
            }
        )
    }
}


