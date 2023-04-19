package dev.xascar.moviesapppro.repository

import dev.xascar.moviesapppro.utils.DataState
import kotlinx.coroutines.flow.StateFlow

interface MoviesRepository {

    val movies: StateFlow<DataState>

    fun getMovies()

}