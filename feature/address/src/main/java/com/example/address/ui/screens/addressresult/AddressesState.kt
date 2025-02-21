package com.example.address.ui.screens.addressresult

import com.example.address.domain.model.AddressItemData

data class AddressesState(
    val errorMessage: String = "",
    val errorCode: Int = -1,
    val isShowErrorMessage: Boolean = false,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = true,
    val addressesData: List<AddressItemData> = emptyList(),
)