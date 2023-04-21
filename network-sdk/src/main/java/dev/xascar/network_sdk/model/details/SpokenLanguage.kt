package dev.xascar.network_sdk.model.details


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @Json(name = "english_name")
    val englishName: String? = null,
    @Json(name = "iso_639_1")
    val iso6391: String? = null,
    @Json(name = "name")
    val name: String? = null
): Serializable