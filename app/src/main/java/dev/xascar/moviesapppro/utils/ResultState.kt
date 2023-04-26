package dev.xascar.moviesapppro.utils

/**
 * Unlike DataState on this package
 * This sealed class can accept any type of response because of generic T on SUCCESS data class
 */
sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    data class Success<T>(val response: T, val page: Int? = 1, val totalPages: Int? = 1) : ResultState<T>()
    data class Error(val error: Exception) : ResultState<Nothing>()
}