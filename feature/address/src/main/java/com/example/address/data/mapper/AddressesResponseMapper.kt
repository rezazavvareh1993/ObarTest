package com.example.address.data.mapper

import com.example.address.data.model.AddressItem
import com.example.address.domain.model.AddressItemData
import com.example.address.domain.networkstate.GetAddressesNetworkState
import retrofit2.HttpException
import javax.inject.Inject

class AddressesResponseMapper @Inject constructor() {
    fun mapToAddressData(
        result: Result<List<AddressItem>>,
    ): GetAddressesNetworkState =
        result.fold(
            onSuccess = { handleOnSuccess(it) },
            onFailure = { handleFailure(it) },
        )

    private fun handleFailure(error: Throwable): GetAddressesNetworkState {
        val errorCode = if (error is HttpException) error.code() else -1
        return GetAddressesNetworkState.Error(
            message = error.message.toString(),
            errorCode = errorCode
        )
    }

    private fun handleOnSuccess(addresses: List<AddressItem>): GetAddressesNetworkState {
        val addressesData = addresses.map { addressItem ->
            AddressItemData(
                firstName = addressItem.first_name,
                lastName = addressItem.last_name,
                address = addressItem.address,
                mobile = addressItem.coordinate_mobile
            )
        }
        return GetAddressesNetworkState.Success(data = addressesData)
    }
}