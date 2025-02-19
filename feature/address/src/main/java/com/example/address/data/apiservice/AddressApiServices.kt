package com.example.address.data.apiservice

import com.example.address.data.model.AddressItem
import com.example.address.data.model.RegisterBody
import com.example.address.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApiServices {
    @POST("api/v1/hipixiApp/Collection/CreateAlbum")
    suspend fun register(
        @Body registerBody: RegisterBody,
    ): Result<RegisterResponse>

    @GET("api/v1/hipixiApp/Collection/GetAlbumById")
    suspend fun getAdresses(
        @Query("collectionId") albumId: String,
    ): Result<List<AddressItem>>
}
