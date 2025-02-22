package com.example.address.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.address.R

@Composable
fun handleErrorMessages(apiError: ApiError): String =
    when (apiError) {
        is ApiError.HttpError -> handleHTTPErrorMessages(apiError.code)
        is ApiError.NetworkError -> stringResource(R.string.connection_error)
        is ApiError.UnknownError -> stringResource(R.string.unknown_error)
    }

@Composable
fun handleHTTPErrorMessages(httpErrorCode: Int): String =
    when (httpErrorCode) {
        401 -> stringResource(R.string.bad_request_error)
        500 -> stringResource(R.string.internal_server_error)
        else -> stringResource(R.string.unknown_error)
    }
