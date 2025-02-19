package com.example.address.domain.model

data class AddressesResult(
    val hasError: Boolean = false,
    val addressesData: List<AddressItemData> = emptyList(),
    val errorMessage: String = "",
    val errorCode: Int = -1
)
