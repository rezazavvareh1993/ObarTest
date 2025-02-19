package com.example.address.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityObject(
    val city_id: Int,
    val city_name: String
)