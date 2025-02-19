package com.example.address.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RegisterBody(
    val address: String,
    val coordinate_mobile: String,
    val coordinate_phone_number: String,
    val first_name: String,
    val gender: String,
    val last_name: String,
    val lat: Double? = null,
    val lng: Double? = null,
    val region: Int = 1
)