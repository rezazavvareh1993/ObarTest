package com.example.address.ui.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(

) : ViewModel() {


    private var mRegisterState = MutableStateFlow(RegisterState(isLoading = false))
    val registerState: StateFlow<RegisterState> = mRegisterState

    fun onEvent(event: RegisterUiEvent) {
        when (event) {
            is RegisterUiEvent.Register -> register(event.name)
        }
    }

    private fun register(name: String) {
        viewModelScope.launch {

        }
    }
}