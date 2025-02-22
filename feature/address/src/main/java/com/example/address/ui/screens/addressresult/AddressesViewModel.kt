package com.example.address.ui.screens.addressresult

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.address.domain.usecase.GetAddressesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressesViewModel @Inject constructor(
    private val getAddressesUseCase: GetAddressesUseCase
) : ViewModel() {
    private var mAddressesState = MutableStateFlow(AddressesState(isLoading = true))
    val addressesState: StateFlow<AddressesState> = mAddressesState.onStart {
        getAddresses()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AddressesState(isLoading = true)
    )

    fun onEvent(event: AddressesUiEvent) {
        when (event) {
            is AddressesUiEvent.OnPulledToRefreshData -> refreshData()

            is AddressesUiEvent.OnErrorMassageChanged ->
                mAddressesState.update {
                    it.copy(
                        errorMessage = event.errorMessage,
                        errorType = null
                    )
                }

            is AddressesUiEvent.HasErrorMessageDisplayed ->
                mAddressesState.update { it.copy(errorMessage = "") }
        }
    }


    private fun refreshData() {
        viewModelScope.launch {
            mAddressesState.update { it.copy(isLoading = true, isRefreshing = true) }
            getAddresses()
        }
    }

    private fun getAddresses() {
        viewModelScope.launch {
            mAddressesState.update { it.copy(isLoading = true) }
            val result = getAddressesUseCase()
            when (result.hasError) {
                true -> mAddressesState.update {
                    it.copy(
                        isLoading = false,
                        isRefreshing = false,
                        errorType = result.errorType,
                    )
                }

                false -> mAddressesState.update {
                    it.copy(
                        addressesData = result.addressesData,
                        isLoading = false,
                        isRefreshing = false
                    )
                }
            }
        }
    }
}