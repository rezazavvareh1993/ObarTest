package com.example.address.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.address.domain.model.RegisterData
import com.example.address.domain.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(val registerUseCase: RegisterUseCase) : ViewModel() {
    private var mRegisterState = MutableStateFlow(RegisterState(isLoading = false))
    val registerState: StateFlow<RegisterState> = mRegisterState

    fun onEvent(event: RegisterUiEvent) {
        when (event) {
            is RegisterUiEvent.OnRegisterClicked -> register(event.registerData)
            is RegisterUiEvent.OnFirstNameChanged ->
                mRegisterState.update { it.copy(firstNameInput = event.newFirstName) }

            is RegisterUiEvent.OnLastNameChanged ->
                mRegisterState.update { it.copy(lastNameInput = event.newLastName) }

            is RegisterUiEvent.OnAddressChanged ->
                mRegisterState.update { it.copy(addressInput = event.newAddress) }

            is RegisterUiEvent.OnMobileChanged ->
                mRegisterState.update { it.copy(mobileInput = event.newMobile) }

            is RegisterUiEvent.OnPhoneNumberChanged ->
                mRegisterState.update { it.copy(phoneNumberInput = event.newPhoneNumber) }

            is RegisterUiEvent.OnGenderTypeChanged ->
                mRegisterState.update { it.copy(genderType = event.newGenderType) }

            is RegisterUiEvent.OnErrorMassageChanged ->
                mRegisterState.update {
                    it.copy(
                        errorMessage = event.errorMessage,
                        errorType = null
                    )
                }

            is RegisterUiEvent.HasErrorMessageDisplayed ->
                mRegisterState.update { it.copy(errorMessage = "") }

            is RegisterUiEvent.HasRegisteredMessageDisplayed ->
                mRegisterState.update { it.copy(hasRegistered = false) }

            is RegisterUiEvent.OnLatLngChanged ->
                mRegisterState.update { it.copy(locationSelected = event.newLatLng) }
        }
    }

    private fun register(registerData: RegisterData) {
        viewModelScope.launch {
            mRegisterState.update { it.copy(isLoading = true) }
            val result = registerUseCase(registerData)
            when (result.hasError) {
                true -> mRegisterState.update {
                    it.copy(
                        isLoading = false,
                        errorType = result.errorType,
                    )
                }

                false -> mRegisterState.update {
                    it.copy(hasRegistered = result.hasRegistered, isLoading = false)
                }
            }
        }
    }
}