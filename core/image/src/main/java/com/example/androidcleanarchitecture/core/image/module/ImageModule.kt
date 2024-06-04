package com.example.androidcleanarchitecture.core.image.module


import coil.ImageLoader
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val imageModule = module {
    single {
        ImageLoader.Builder(androidApplication())
            .crossfade(350)
            .callFactory(get<OkHttpClient>())
            .build()
    }
}