package dev.xascar.network_sdk.model.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class Genre(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "name")
    val name: String? = null
): Serializable