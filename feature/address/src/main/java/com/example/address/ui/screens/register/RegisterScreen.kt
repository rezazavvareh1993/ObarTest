package com.example.address.ui.screens.register

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.address.domain.model.RegisterData
import com.example.ui.component.ShowToast

@Composable
fun RegisterScreen(
    registerUiEvent: (RegisterUiEvent) -> Unit,
    registerState: RegisterState,
    navigateToAddressResult: () -> Unit
) {

    val context = LocalContext.current
    if (registerState.hasRegistered) {
        ShowToast(context, "Registered")
        registerUiEvent(RegisterUiEvent.HasRegisteredMessageDisplayed)
    }

    if (registerState.errorMessage.isNotEmpty()) {
        ShowToast(context, "${registerState.errorMessage} ${registerState.errorCode}")
        registerUiEvent(RegisterUiEvent.HasErrorMessageDisplayed)
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
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
                label = { Text("First Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = registerState.lastNameInput,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnLastNameChanged(it))
                },
                label = { Text("Last Name") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = registerState.mobileInput,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnMobileChanged(it))
                },
                label = { Text("Mobile Phone") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = registerState.phoneNumberInput,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnPhoneNumberChanged(it))
                },
                label = { Text("Home Phone") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = registerState.addressInput,
                onValueChange = {
                    registerUiEvent(RegisterUiEvent.OnAddressChanged(it))
                },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                enabled = !registerState.isLoading,
                content = {
                    Text("Register")
                },
                onClick = {
                    registerUiEvent(
                        RegisterUiEvent.OnRegisterClicked(
                            registerData = RegisterData(
                                firstName = registerState.firstNameInput,
                                lastName = registerState.lastNameInput,
                                address = registerState.addressInput,
                                mobileNumber = registerState.mobileInput,
                                phoneNumber = registerState.phoneNumberInput,
                                lat = registerState.lat,
                                lng = registerState.lng,
                                gender = registerState.genderType
                            )
                        )
                    )
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Gender:")
                listOf("Male", "Female").forEach { gender ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = registerState.genderType == gender,
                            onClick = {
                                registerUiEvent(RegisterUiEvent.OnGenderTypeChanged(gender))
                            }
                        )
                        Text(text = gender)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .border(1.dp, Color.Gray)
            ) {
                Text(
                    "Map Placeholder (Integrate Google Maps Here)",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
