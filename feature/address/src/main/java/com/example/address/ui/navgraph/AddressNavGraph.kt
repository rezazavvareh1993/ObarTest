package com.example.address.ui.navgraph

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.address.ui.screens.addressresult.AddressesScreen
import com.example.address.ui.screens.addressresult.AddressesViewModel
import com.example.address.ui.screens.register.RegisterScreen
import com.example.address.ui.screens.register.RegisterViewModel
import com.example.ui.graphroutes.GraphRoutes

fun NavGraphBuilder.addressNavGraph(
    navController: NavHostController,
) {
    navigation<GraphRoutes.Address>(
        startDestination = AddressScreens.Register.route,
    ) {
        composable<AddressScreensGraph.Register> {
            val viewModel = hiltViewModel<RegisterViewModel>()
            val registerUiEvent = viewModel::onEvent
            val registerState by viewModel.registerState.collectAsStateWithLifecycle()
            RegisterScreen(
                registerUiEvent = registerUiEvent,
                registerState = registerState,
                navigateToAddressResult = {
                    navController.navigate(AddressScreens.Addresses.route)
                },
            )
        }

        composable<AddressScreensGraph.Addresses> {
            val viewModel = hiltViewModel<AddressesViewModel>()
            val addressesUiEvent = viewModel::onEvent
            val addressesState by viewModel.addressesState.collectAsStateWithLifecycle()
            AddressesScreen(
                addressesUiEvent = addressesUiEvent,
                addressesState = addressesState,
                onBackClicked = {
                    navController.popBackStack()
                },
            )
        }
    }
}
