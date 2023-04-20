package dev.xascar.network_sdk.utils

import dev.xascar.network_sdk.model.details.DetailsResponse

interface MovieDetailsCallback {
    fun onSuccess(movie: DetailsResponse)
    fun onError(e: Exception)
}