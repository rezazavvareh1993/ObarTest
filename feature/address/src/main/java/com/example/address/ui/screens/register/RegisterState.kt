package com.example.address.ui.screens.register

import com.google.android.gms.maps.model.LatLng

data class RegisterState(
    val errorMessage: String = "",
    val errorCode: Int = -1,
    val isShowErrorMessage: Boolean = false,
    val isLoading: Boolean = false,
    val firstNameInput: String = "",
    val lastNameInput: String = "",
    val addressInput: String = "",
    val mobileInput: String = "",
    val phoneNumberInput: String = "",
    val genderType: String = "Male",
    val locationSelected: LatLng = LatLng(0.0, 0.0),
    val lng: Double = 0.0,
    val hasRegistered: Boolean = false,
)