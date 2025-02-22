package com.example.address.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Region(
    val city_object: CityObject,
    val id: Int,
    val name: String,
    val state_object: StateObject
)