package dev.xascar.moviesapppro.repository

import dev.xascar.moviesapppro.domain.MovieDomain
import dev.xascar.moviesapppro.utils.ResultState
import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.model.details.DetailsResponse
import dev.xascar.network_sdk.utils.DataState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import retrofit2.Response

interface MoviesRepository {

    val movies: StateFlow<DataState>
    //val details: BehaviorSubject<DetailsResponse>

    /**
     * Network calls from the sdk
     */
    fun getMovies(page: Int = 1)

    /**
     * Network calls from the sdk using RxJava
     */
    fun getPopularMovies(page: Int = 1): Flow<ResultState<List<MovieDomain>>>
    fun getUpcomingMovies(page: Int = 1): Flow<ResultState<List<MovieDomain>>>
    fun getNowPlayingMovies(page: Int = 1): Flow<ResultState<List<MovieDomain>>>
    fun getMovieDetails(movieId: String): Single<DetailsResponse>

}