package com.example.address.domain.networkstate

import com.example.address.domain.model.AddressItemData

sealed class GetAddressesNetworkState {
    data class Success(val data: List<AddressItemData>) : GetAddressesNetworkState()

    data class Error(val message: String, val errorCode: Int) : GetAddressesNetworkState()
}