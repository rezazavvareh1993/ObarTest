package com.example.address.domain.model

import com.example.address.util.ApiError

data class RegisterResult(
    val hasError: Boolean = false,
    val hasRegistered: Boolean = false,
    val errorType: ApiError? = null
)
