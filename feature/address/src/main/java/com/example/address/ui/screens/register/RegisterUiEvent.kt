package com.example.address.ui.screens.register

import com.example.address.domain.model.RegisterData

sealed class RegisterUiEvent {
    data class OnRegisterClicked(val registerData: RegisterData) : RegisterUiEvent()
    data class OnFirstNameChanged(val newFirstName: String) : RegisterUiEvent()
    data class OnLastNameChanged(val newLastName: String) : RegisterUiEvent()
    data class OnAddressChanged(val newAddress: String) : RegisterUiEvent()
    data class OnMobileChanged(val newMobile: String) : RegisterUiEvent()
    data class OnPhoneNumberChanged(val newPhoneNumber: String) : RegisterUiEvent()
    data class OnGenderTypeChanged(val newGenderType: String) : RegisterUiEvent()
    data object HasErrorMessageDisplayed : RegisterUiEvent()
    data object HasRegisteredMessageDisplayed : RegisterUiEvent()
}