package com.example.address.data

import com.example.address.data.apiservice.AddressApiServices
import com.example.address.data.mapper.AddressesResponseMapper
import com.example.address.data.mapper.RegisterBodyMapper
import com.example.address.data.mapper.RegisterResponseMapper
import com.example.address.domain.AddressRepository
import com.example.address.domain.model.RegisterData
import com.example.address.domain.networkstate.GetAddressesNetworkState
import com.example.address.domain.networkstate.RegisterNetworkState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class AddressRepositoryImpl @Inject constructor(
    val addressApiServices: AddressApiServices,
    val registerBodyMapper: RegisterBodyMapper,
    val registerResponseMapper: RegisterResponseMapper,
    val addressesResponseMapper: AddressesResponseMapper,
    val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AddressRepository {
    override suspend fun register(registerData: RegisterData): RegisterNetworkState =
        withContext(dispatcher) {
            val registerBody = registerBodyMapper.mapInputDataToRequestBody(registerData)
            val result = addressApiServices.register(registerBody)
            registerResponseMapper.mapToRegisterData(result)
        }

    override suspend fun getAddresses(): GetAddressesNetworkState =
        withContext(dispatcher) {
            val result = addressApiServices.getAddresses()
            addressesResponseMapper.mapToAddressData(result)
        }
}
