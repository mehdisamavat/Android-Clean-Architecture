package com.example.androidcleanarchitecture.core.network.handler

import com.example.androidcleanarchitecture.core.common.NetworkResult
import retrofit2.HttpException
import retrofit2.Response

fun <T : Any> handleApi(execute: () -> Response<T>): NetworkResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkResult.Success(body)
        } else {
            NetworkResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkResult.Exception(e)
    }
}


suspend fun <T : Any> NetworkResult<T>.onSuccess(executable: suspend (T) -> Unit): NetworkResult<T> =
    apply {
        if (this is NetworkResult.Success<T>) {
            executable(data)
        }
    }

suspend fun <T : Any> NetworkResult<T>.onError(executable: suspend (code: Int, message: String?) -> Unit): NetworkResult<T> =
    apply {
        if (this is NetworkResult.Error<T>) {
            executable(code, message)
        }
    }

suspend fun <T : Any> NetworkResult<T>.onException(executable: suspend (e: Throwable) -> Unit): NetworkResult<T> =
    apply {
        if (this is NetworkResult.Exception<T>) {
            executable(e)
        }
    }