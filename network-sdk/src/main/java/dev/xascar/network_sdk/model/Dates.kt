package dev.xascar.network_sdk.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Dates(
    @Json(name = "maximum")
    val maximum: String? = null,
    @Json(name = "minimum")
    val minimum: String? = null
)