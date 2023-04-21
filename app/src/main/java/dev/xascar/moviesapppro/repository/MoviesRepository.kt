package dev.xascar.moviesapppro.repository

import dev.xascar.network_sdk.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface MoviesRepository {

    val movies: StateFlow<DataState>

    fun getMovies(page: Int)

    fun getPopularMovies(page: Int): Flow<StateFlow<DataState>>
    fun getUpcomingMovies(): StateFlow<DataState>
    fun getNowPlayingMovies(): StateFlow<DataState>
    fun getMovieDetails(): Flow<DataState>

}