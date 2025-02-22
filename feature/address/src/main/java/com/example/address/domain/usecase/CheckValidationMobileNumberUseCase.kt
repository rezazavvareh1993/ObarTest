package com.example.address.domain.usecase

import com.example.address.ui.screens.register.MobileValidation
import com.example.address.util.extensions.isValidMobileNumber
import javax.inject.Inject

class CheckValidationMobileNumberUseCase @Inject constructor() {
    operator fun invoke(mobileNumber: String): MobileValidation =
        MobileValidation(
            isShowErrorMessage = mobileNumber.length == 11 && !mobileNumber.isValidMobileNumber(),
            isValidMobileNumber = mobileNumber.isValidMobileNumber(),
        )
}
