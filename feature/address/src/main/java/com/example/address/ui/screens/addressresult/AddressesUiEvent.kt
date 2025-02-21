package com.example.address.ui.screens.addressresult

sealed class AddressesUiEvent {
    data object HasErrorMessageDisplayed : AddressesUiEvent()
    data object OnPulledToRefreshData : AddressesUiEvent()
}