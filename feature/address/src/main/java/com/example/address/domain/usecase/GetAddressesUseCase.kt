package com.example.address.domain.usecase

import com.example.address.domain.AddressRepository
import com.example.address.domain.model.AddressesResult
import com.example.address.domain.networkstate.GetAddressesNetworkState
import javax.inject.Inject

class GetAddressesUseCase @Inject constructor(private val repository: AddressRepository) {
    suspend operator fun invoke(): AddressesResult =
        when (val response = repository.getAddresses()) {
            is GetAddressesNetworkState.Error -> AddressesResult(
                errorType = response.apiError,
                hasError = true,
            )

            is GetAddressesNetworkState.Success -> AddressesResult(
                hasError = false,
                addressesData = response.data
            )
        }
}
