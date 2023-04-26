package dev.xascar.moviesapppro.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.xascar.network_sdk.model.Result
import dev.xascar.network_sdk.utils.MovieCategory

@Entity(tableName = "movies_table")
data class DomainMovie(
    @PrimaryKey val id: Int,
    val genreIds: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val title: String,
    val video: Boolean,
    val movieTypes: MovieCategory
)

fun List<Result?>?.mapToDomainMovie(types: MovieCategory): List<DomainMovie> =
    this?.map {
        DomainMovie(
            id = it?.id ?: 0,
            video = it?.video ?: false,
            title = it?.title ?: "NOT AVAILABLE",
            posterPath = it?.posterPath ?: "",
            popularity = it?.popularity ?: -1.0,
            overview = it?.overview ?: "NOT AVAILABLE",
            genreIds = it?.genreIds.toString(),
            movieTypes = types
        )
    } ?: emptyList()