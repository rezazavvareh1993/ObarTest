package com.example.address.ui.screens.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.address.R
import com.example.address.domain.model.RegisterData
import com.example.address.util.handleErrorMessages
import com.example.ui.component.ObarToolbar
import com.example.ui.component.ShowToast
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


private const val MALE_GENDER = "Male"
private const val FEMALE_GENDER = "Female"

@Composable
fun RegisterScreen(
    registerUiEvent: (RegisterUiEvent) -> Unit,
    registerState: RegisterState,
    navigateToAddressResult: () -> Unit
) {

    val context = LocalContext.current

    if (registerState.hasRegistered) {
        ShowToast(context, stringResource(R.string.registered))
        registerUiEvent(RegisterUiEvent.HasRegisteredMessageDisplayed)
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(registerState.locationSelected, 10f)
    }

    if (registerState.errorMessage.isNotEmpty()) {
        ShowToast(context, registerState.errorMessage)
        registerUiEvent(RegisterUiEvent.HasErrorMessageDisplayed)
    }

    if (registerState.errorType != null) {
        registerUiEvent(RegisterUiEvent.OnErrorMassageChanged(handleErrorMessages(registerState.errorType)))
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ObarToolbar(
                title = stringResource(R.string.register),
                textButtonTitle = stringResource(R.string.show_addresses)
            ) {
                navigateToAddressResult()
            }
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = registerState.firstNameInput,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnFirstNameChanged(it))
                },
                label = { Text(stringResource(R.string.first_name)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = registerState.lastNameInput,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnLastNameChanged(it))
                },
                label = { Text(stringResource(R.string.last_name)) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
            )

            OutlinedTextField(
                value = registerState.mobileNumberInfo.mobileNumber,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnMobileChanged(it))
                },
                label = { Text(stringResource(R.string.mobile)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = registerState.mobileNumberInfo.mobileValidation.isShowErrorMessage,
                singleLine = true,
                supportingText = if (registerState.mobileNumberInfo.mobileValidation.isShowErrorMessage) {
                    {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Right,
                            text = stringResource(id = R.string.mobile_number_wrong_error),
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                } else {
                    null
                }
            )

            OutlinedTextField(
                value = registerState.phoneNumberInput.phoneNumber,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnPhoneNumberChanged(it))
                },
                label = { Text(stringResource(R.string.phone_number)) },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                isError = registerState.phoneNumberInput.phoneNumberValidation.isShowErrorMessage,
                supportingText = if (registerState.phoneNumberInput.phoneNumberValidation.isShowErrorMessage) {
                    {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Right,
                            text = stringResource(id = R.string.phone_number_wrong_error),
                            color = MaterialTheme.colorScheme.error,
                        )
                    }
                } else {
                    null
                }
            )

            OutlinedTextField(
                value = registerState.addressInput,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnAddressChanged(it))
                },
                label = { Text(stringResource(R.string.address)) },
                modifier = Modifier.fillMaxWidth(),
                maxLines = 2
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(stringResource(R.string.gender))
                mapOf(
                    MALE_GENDER to stringResource(R.string.man),
                    FEMALE_GENDER to stringResource(R.string.woman)
                ).forEach { gender ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = registerState.genderType == gender.key,
                            onClick = {
                                registerUiEvent(RegisterUiEvent.OnGenderTypeChanged(gender.key))
                            }
                        )
                        Text(text = gender.value)
                    }
                }
            }

            if (registerState.isLoading) {
                AnimatedVisibility(visible = true) { CircularProgressIndicator() }
            } else {
                Button(
                    content = {
                        Text(stringResource(R.string.register))
                    },
                    enabled = checkValidationInputs(registerState),
                    onClick = {
                        registerUiEvent(
                            RegisterUiEvent.OnRegisterClicked(
                                registerData = RegisterData(
                                    firstName = registerState.firstNameInput,
                                    lastName = registerState.lastNameInput,
                                    address = registerState.addressInput,
                                    mobileNumber = registerState.mobileNumberInfo.mobileNumber,
                                    phoneNumber = registerState.phoneNumberInput.phoneNumber,
                                    lat = registerState.locationSelected.latitude,
                                    lng = registerState.locationSelected.longitude,
                                    gender = registerState.genderType
                                )
                            )
                        )
                    }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            ) {
                GoogleMap(
                    modifier = Modifier.matchParentSize(),
                    cameraPositionState = cameraPositionState,
                    onMapClick = { latLng ->
                        registerUiEvent(RegisterUiEvent.OnLatLngChanged(newLatLng = latLng))
                    }
                ) {
                    Marker(
                        state = MarkerState(position = registerState.locationSelected),
                        title = stringResource(R.string.selected_location)
                    )
                }
            }

            Text(
                text = stringResource(
                    R.string.lat_lng_detail,
                    registerState.locationSelected.latitude,
                    registerState.locationSelected.longitude
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

private fun checkValidationInputs(registerState: RegisterState): Boolean =
    registerState.firstNameInput.isNotEmpty() && registerState.lastNameInput.isNotEmpty() &&
            registerState.addressInput.isNotEmpty() && registerState.mobileNumberInfo.mobileValidation.isValidMobileNumber &&
            registerState.phoneNumberInput.phoneNumberValidation.isValidPhoneNumber


@PreviewFontScale
@PreviewScreenSizes
@PreviewLightDark
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        registerUiEvent = {},
        registerState = RegisterState(
            firstNameInput = "Ali",
            lastNameInput = "Rezaei",
            addressInput = "Tehran, Iran",
            mobileNumberInfo = MobileInputInfo(
                mobileNumber = "09123456789",
                mobileValidation = MobileValidation(
                    isShowErrorMessage = false,
                    isValidMobileNumber = true
                )
            ),
            phoneNumberInput = PhoneInputInfo(
                phoneNumber = "02112345678",
                phoneNumberValidation = PhoneValidation(
                    isShowErrorMessage = false,
                    isValidPhoneNumber = true
                )
            ),
        ),
        navigateToAddressResult = {}
    )
}
