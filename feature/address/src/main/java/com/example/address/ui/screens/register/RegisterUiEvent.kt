package com.example.address.ui.screens.register

sealed class RegisterUiEvent {
    data class Register(val name: String) : RegisterUiEvent()
}