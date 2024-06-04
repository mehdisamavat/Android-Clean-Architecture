package com.example.androidcleanarchitecture.core.network

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.androidcleanarchitecture.core.network.Constant.BASE_URL
import com.example.androidcleanarchitecture.core.network.handler.NetworkResultCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        OkHttpClient.Builder().callTimeout(Constant.TIME_OUT, TimeUnit.MILLISECONDS)
            .connectTimeout(Constant.TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constant.TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(Constant.TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor(ChuckerInterceptor(androidApplication()))
            .build()
    }

    single {
        GsonBuilder().create()
    }


    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .callFactory { request -> get<OkHttpClient>().newCall(request) }
//            .addConverterFactory(get<Json>().asConverterFactory("application/json".toMediaType()))
//            .addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(NetworkResultCallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}
