package dev.xascar.moviesapppro.utils

sealed class ResultState<out T> {
    object Loading : ResultState<Nothing>()
    data class Success<T>(val response: T) : ResultState<T>()
    data class Error<T>(val error: Exception) : ResultState<T>()
}