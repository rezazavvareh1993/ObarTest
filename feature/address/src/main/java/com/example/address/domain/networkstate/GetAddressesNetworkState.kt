package com.example.address.domain.networkstate

import com.example.address.domain.model.AddressItemData
import com.example.address.util.ApiError

sealed class GetAddressesNetworkState {
    data class Success(val data: List<AddressItemData>) : GetAddressesNetworkState()

    data class Error(val apiError: ApiError) : GetAddressesNetworkState()
}