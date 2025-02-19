package com.example.address.data.mapper

import com.example.address.data.model.RegisterBody
import com.example.address.domain.model.RegisterData
import javax.inject.Inject

class RegisterBodyMapper @Inject constructor() {
    fun mapInputDataToRequestBody(data: RegisterData) : RegisterBody =
        RegisterBody(
            first_name = data.firstName,
            last_name = data.lastName,
            lat = data.lat,
            lng = data.lng,
            address = data.address,
            coordinate_mobile = data.mobileNumber,
            coordinate_phone_number = data.phoneNumber,
            gender = data.gender
        )
}