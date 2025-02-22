package com.example.address.ui.screens.register

import com.example.address.util.ApiError
import com.google.android.gms.maps.model.LatLng

data class RegisterState(
    val errorMessage: String = "",
    val errorType: ApiError? = null,
    val isShowErrorMessage: Boolean = false,
    val isLoading: Boolean = false,
    val firstNameInput: String = "",
    val lastNameInput: String = "",
    val addressInput: String = "",
    val mobileNumberInfo: MobileInputInfo = MobileInputInfo(),
    val phoneNumberInput: PhoneInputInfo = PhoneInputInfo(),
    val genderType: String = "Male",
    val locationSelected: LatLng = LatLng(0.0, 0.0),
    val lng: Double = 0.0,
    val hasRegistered: Boolean = false,
)

data class MobileInputInfo(
    val mobileNumber: String = "",
    val mobileValidation: MobileValidation = MobileValidation(),
)

data class MobileValidation(
    val isShowErrorMessage: Boolean = false,
    val isValidMobileNumber: Boolean = false,
)


data class PhoneInputInfo(
    val phoneNumber: String = "",
    val phoneNumberValidation: PhoneValidation = PhoneValidation(),
)

data class PhoneValidation(
    val isShowErrorMessage: Boolean = false,
    val isValidPhoneNumber: Boolean = false,
)
