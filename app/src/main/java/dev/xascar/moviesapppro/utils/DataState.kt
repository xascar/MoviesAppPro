package dev.xascar.moviesapppro.utils

import dev.xascar.network_sdk.model.Result

/**
 * Unlike ResultState on this package
 * This sealed class can only accept one type of response (List<Result?>?) on SUCCESS data class
 */
sealed class DataState {
    object LOADING : DataState()
    data class SUCCESS(val response: List<Result?>? = null) : DataState()
    data class ERROR(val error: Exception) : DataState()
}