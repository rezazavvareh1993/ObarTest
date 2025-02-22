package com.example.address.data.mapper

import com.example.address.data.model.RegisterResponse
import com.example.address.domain.networkstate.RegisterNetworkState
import com.example.address.util.extensions.toApiError
import javax.inject.Inject

class RegisterResponseMapper @Inject constructor() {
    fun mapToRegisterData(
        result: Result<RegisterResponse>,
    ): RegisterNetworkState =
        result.fold(
            onSuccess = { handleOnSuccess(it) },
            onFailure = { handleFailure(it) },
        )

    private fun handleFailure(error: Throwable): RegisterNetworkState {
        return RegisterNetworkState.Error(apiError = error.toApiError())
    }

    private fun handleOnSuccess(registerResponse: RegisterResponse): RegisterNetworkState {
        return RegisterNetworkState.Success(data = true)
    }
}
