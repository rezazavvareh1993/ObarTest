package com.example.address.domain

import com.example.address.domain.model.RegisterData
import com.example.address.domain.networkstate.GetAddressesNetworkState
import com.example.address.domain.networkstate.RegisterNetworkState

interface AddressRepository {

    suspend fun register(registerData: RegisterData): RegisterNetworkState

    suspend fun getAddresses(): GetAddressesNetworkState
}