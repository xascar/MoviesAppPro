package dev.xascar.moviesapppro.utils

import dev.xascar.network_sdk.model.Result

sealed class DataState {
    object LOADING : DataState()
    data class SUCCESS(val response: List<Result?>? = null) : DataState()
    data class ERROR(val error: Exception) : DataState()
}