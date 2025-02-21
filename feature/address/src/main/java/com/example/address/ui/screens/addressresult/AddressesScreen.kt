package com.example.address.ui.screens.addressresult

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.address.R
import com.example.address.ui.screens.addressresult.component.AddressItemComponent
import com.example.ui.component.ObarToolbar
import com.example.ui.component.ShowToast

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressesScreen(
    addressesUiEvent: (AddressesUiEvent) -> Unit,
    addressesState: AddressesState,
    onBackClicked: () -> Unit
) {
    val context = LocalContext.current

    if (addressesState.errorMessage.isNotEmpty()) {
        ShowToast(context, "${addressesState.errorMessage} ${addressesState.errorCode}")
        addressesUiEvent(AddressesUiEvent.HasErrorMessageDisplayed)
    }

    val lazyListState = rememberLazyListState()
    val pullToRefreshState = rememberPullToRefreshState()


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            ObarToolbar(
                title = stringResource(R.string.show_addresses),
                textButtonTitle = stringResource(R.string.back)
            ) {
                onBackClicked()
            }
        }) { padding ->
        PullToRefreshBox(
            modifier =
            Modifier
                .fillMaxSize()
                .padding(padding)
                .background(color = MaterialTheme.colorScheme.surface)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            state = pullToRefreshState,
            isRefreshing = addressesState.isRefreshing,
            onRefresh = {
                addressesUiEvent(AddressesUiEvent.OnPulledToRefreshData)
            },
        ) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AnimatedVisibility(visible = addressesState.isLoading && !addressesState.isRefreshing) { CircularProgressIndicator() }

                if (addressesState.addressesData.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 8.dp),
                        state = lazyListState,
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(addressesState.addressesData) { item ->
                            AddressItemComponent(item)
                        }
                    }
                }
            }
        }
    }
}