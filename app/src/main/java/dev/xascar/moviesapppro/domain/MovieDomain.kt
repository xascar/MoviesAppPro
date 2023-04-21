package dev.xascar.moviesapppro.domain

import dev.xascar.network_sdk.model.Result

data class MovieDomain(
    val id: String,
    val title: String,
    val poster: String,
    val genres: List<Int?>,
    val overview: String,
    val video: Boolean,
    val popularity: Double
)

fun List<Result?>?.mapToDomainMovie(): List<MovieDomain>{
    return this?.map {
         MovieDomain(
             title = it?.title ?: "NOT AVAILABLE",
             id = it?.id.toString(),
             poster = it?.posterPath ?: "",
             genres = it?.genreIds ?: emptyList(),
            overview = it?.overview ?: "NOT AVAILABLE",
            video = it?.video ?: false,
            popularity = it?.popularity ?: -0.0
         )
     } ?: emptyList()
}