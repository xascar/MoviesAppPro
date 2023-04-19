package dev.xascar.network_sdk.model.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompany(
    @Json(name = "id")
    val id: Int? = null,
    @Json(name = "logo_path")
    val logoPath: String? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "origin_country")
    val originCountry: String? = null
)