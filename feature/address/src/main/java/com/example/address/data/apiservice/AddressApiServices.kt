package com.example.address.data.apiservice

import com.example.address.data.model.AddressItem
import com.example.address.data.model.RegisterBody
import com.example.address.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AddressApiServices {
    @POST("api/karfarmas/address")
    suspend fun register(
        @Body registerBody: RegisterBody,
    ): Result<RegisterResponse>

    @GET("api/karfarmas/address")
    suspend fun getAddresses(): Result<List<AddressItem>>
}
