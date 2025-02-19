package com.example.address.domain.networkstate

sealed class RegisterNetworkState {
    data class Success(val data: Boolean) : RegisterNetworkState()

    data class Error(val message: String, val errorCode: Int) : RegisterNetworkState()
}