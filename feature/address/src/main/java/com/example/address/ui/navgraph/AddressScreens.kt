package com.example.address.ui.navgraph

import kotlinx.serialization.Serializable

object AddressScreensGraph {
    @Serializable
    data object Register

    @Serializable
    data object Addresses
}

@Serializable
sealed class AddressScreens<T>(val route: T) {
    @Serializable
    data object Register : AddressScreens<AddressScreensGraph.Register>(
        route = AddressScreensGraph.Register,
    )

    @Serializable
    data object Addresses : AddressScreens<AddressScreensGraph.Addresses>(
        route = AddressScreensGraph.Addresses,
    )
}