package com.example.androidcleanarchitecture

import android.app.Application
import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderFactory
import com.example.androidcleanarchitecture.core.common.dispatcher.dispatcherModule
import com.example.androidcleanarchitecture.core.di.dataModules
import com.example.androidcleanarchitecture.core.di.domainModules
import com.example.androidcleanarchitecture.core.di.viewModelModules
import com.example.androidcleanarchitecture.core.image.module.imageModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class App : Application(), ImageLoaderFactory {
    private val imageLoader: ImageLoader by lazy { get() }

    override fun onCreate() {
        super.onCreate()
        configureDi()
    }

    override fun newImageLoader(): ImageLoader = imageLoader

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    private fun configureDi() =
        startKoin {
            modules(
                domainModules,
                viewModelModules,
                dataModules,
                imageModule,
                dispatcherModule
            )
            androidContext(this@App)
        }
}
