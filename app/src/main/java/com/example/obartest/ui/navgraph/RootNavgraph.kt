package com.example.obartest.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.address.ui.navgraph.addressNavGraph
import com.example.ui.graphroutes.GraphRoutes

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = GraphRoutes.Address
    ) {
        addressNavGraph(navController = navController)
    }
}