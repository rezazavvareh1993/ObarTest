package com.example.address.domain.usecase

import com.example.address.domain.AddressRepository
import com.example.address.domain.model.RegisterData
import com.example.address.domain.model.RegisterResult
import com.example.address.domain.networkstate.RegisterNetworkState
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val repository: AddressRepository) {
    suspend operator fun invoke(registerData: RegisterData): RegisterResult =
        when (val response = repository.register(registerData)) {
            is RegisterNetworkState.Error -> RegisterResult(
                errorMessage = response.message,
                hasError = true,
                errorCode = response.errorCode
            )

            is RegisterNetworkState.Success -> RegisterResult(
                hasError = false,
                hasRegistered = response.data
            )
        }
}
