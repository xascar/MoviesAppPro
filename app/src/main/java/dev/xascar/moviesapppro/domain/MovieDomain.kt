package dev.xascar.moviesapppro.domain

import dev.xascar.network_sdk.model.Result

/**
 * Differentiate the objects that are coming from the json objet and parse it to a
 * better holder for the app
 */


//data class MovieDomain(
//    val id: String,
//    val title: String,
//    val poster: String,
//    //todo create a type converter for the list or any complex object
//    val genres: List<Int?>,
//    val overview: String,
//    val video: Boolean,
//    val popularity: Double
//)
//
//fun List<Result?>?.mapToDomainMovie(): List<MovieDomain>{
//    return this?.map {
//         MovieDomain(
//             title = it?.title ?: "NOT AVAILABLE",
//             id = it?.id.toString(),
//             poster = it?.posterPath ?: "",
//             genres = it?.genreIds ?: emptyList(),
//            overview = it?.overview ?: "NOT AVAILABLE",
//            video = it?.video ?: false,
//            popularity = it?.popularity ?: -0.0
//         )
//     } ?: emptyList()
//}