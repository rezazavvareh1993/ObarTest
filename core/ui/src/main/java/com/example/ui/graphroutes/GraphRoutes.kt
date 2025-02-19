package com.example.ui.graphroutes

import kotlinx.serialization.Serializable

@Serializable
sealed class GraphRoutes {
    @Serializable
    object Address
}