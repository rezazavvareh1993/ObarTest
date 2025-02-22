package com.example.address.domain.usecase

import com.example.address.ui.screens.register.PhoneValidation
import com.example.address.util.extensions.isValidIranianLandline
import javax.inject.Inject

class CheckValidationPhoneNumberUseCase @Inject constructor() {
    operator fun invoke(phoneNumber: String): PhoneValidation =
        PhoneValidation(
            isShowErrorMessage = phoneNumber.length == 11 && !phoneNumber.isValidIranianLandline(),
            isValidPhoneNumber = phoneNumber.isValidIranianLandline(),
        )
}
