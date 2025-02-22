package com.example.address.util.extensions

fun String.isValidMobileNumber(): Boolean {
    val iranianMobileRegex = "^09\\d{9}$".toRegex()
    return iranianMobileRegex.matches(this)
}

fun String.isValidIranianLandline(): Boolean {
    val regex = Regex("^0[1-9]{2}[1-9][0-9]{7}$")
    return this.matches(regex)
}