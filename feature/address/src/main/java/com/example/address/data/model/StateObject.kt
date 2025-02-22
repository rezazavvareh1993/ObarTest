package com.example.address.data.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StateObject(
    val state_id: Int,
    val state_name: String
)