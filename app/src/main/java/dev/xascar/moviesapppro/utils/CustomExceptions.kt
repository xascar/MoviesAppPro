package dev.xascar.moviesapppro.utils

/**
 * Exception when null response
 */
class NullResponseException: Exception() {
    override val message: String
        get() = "Null response exception"

}

/**
 * Exception when unsuccessful response
 */
class UnsuccessfulResponseException(message: String?): Exception(message)