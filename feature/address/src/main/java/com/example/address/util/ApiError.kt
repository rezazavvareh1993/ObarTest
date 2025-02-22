package com.example.address.util

sealed class ApiError {
    data object NetworkError : ApiError()
    data class HttpError(val code: Int, val message: String) : ApiError()
    data object UnknownError : ApiError()
}