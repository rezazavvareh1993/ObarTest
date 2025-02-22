package com.example.address.domain.networkstate

import com.example.address.util.ApiError

sealed class RegisterNetworkState {
    data class Success(val data: Boolean) : RegisterNetworkState()

    data class Error(val apiError: ApiError) : RegisterNetworkState()
}