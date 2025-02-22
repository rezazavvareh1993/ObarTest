package com.example.address.ui.screens.addressresult

import com.example.address.domain.model.AddressItemData
import com.example.address.util.ApiError

data class AddressesState(
    val errorMessage: String = "",
    val errorType: ApiError? = null,
    val isShowErrorMessage: Boolean = false,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val addressesData: List<AddressItemData> = emptyList(),
)