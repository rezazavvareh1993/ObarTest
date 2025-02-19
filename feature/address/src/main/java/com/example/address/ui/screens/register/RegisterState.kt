package com.example.address.ui.screens.register

data class RegisterState(
    val errorMessage: String = "",
    val isShowErrorMessage: Boolean = false,
    val isLoading: Boolean = false,
)