package com.example.address.domain.model

data class RegisterResult(
    val hasError: Boolean = false,
    val hasRegistered: Boolean = false,
    val errorMessage: String = "",
    val errorCode: Int = -1
)
