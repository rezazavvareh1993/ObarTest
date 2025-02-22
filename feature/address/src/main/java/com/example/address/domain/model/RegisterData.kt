package com.example.address.domain.model

data class RegisterData(
    val firstName: String,
    val lastName: String,
    val lat: Double? = null,
    val lng: Double? = null,
    val mobileNumber: String,
    val phoneNumber: String,
    val address: String,
    val gender: String
)
