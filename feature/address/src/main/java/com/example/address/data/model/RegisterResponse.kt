package com.example.address.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterResponse(
    val address: String,
    val address_id: String,
    val coordinate_mobile: String,
    val coordinate_phone_number: String,
    @Json(name = "first_name")
    val firstName: String,
    val gender: String,
    val id: String,
    @Json(name = "last_name")
    val lastName: String,
    val lat: Double,
    val lng: Double,
    val region: Region
)
