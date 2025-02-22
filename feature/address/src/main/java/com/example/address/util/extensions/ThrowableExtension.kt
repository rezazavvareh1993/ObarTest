package com.example.address.util.extensions

import com.example.address.util.ApiError
import retrofit2.HttpException
import java.io.IOException

fun Throwable.toApiError(): ApiError {
    return when (this) {
        is IOException -> ApiError.NetworkError
        is HttpException -> ApiError.HttpError(this.code(), this.message())
        else -> ApiError.UnknownError
    }
}