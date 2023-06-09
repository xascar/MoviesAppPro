package dev.xascar.network_sdk.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    @Json(name = "dates")
    val dates: Dates? = null,
    @Json(name = "page")
    val page: Int? = null,
    @Json(name = "results")
    val results: List<Result?>? = null,
    @Json(name = "total_pages")
    val totalPages: Int? = null,
    @Json(name = "total_results")
    val totalResults: Int? = null
): Serializable