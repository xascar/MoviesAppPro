package dev.xascar.network_sdk.utils

import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.model.Result

sealed class DataState {
    object LOADING : DataState()
    data class SUCCESS(val response: MoviesResponse? = null) : DataState()
    data class ERROR(val error: Exception) : DataState()
}