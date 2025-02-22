package com.example.address.domain.model

import com.example.address.util.ApiError

data class AddressesResult(
    val hasError: Boolean = false,
    val addressesData: List<AddressItemData> = emptyList(),
    val errorType: ApiError? = null,
)
