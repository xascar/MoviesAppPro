package dev.xascar.moviesapppro.repository

import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.moviesapppro.utils.ResultState
import dev.xascar.network_sdk.model.details.DetailsResponse
import dev.xascar.network_sdk.utils.DataState
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MoviesRepository {

    val movies: StateFlow<DataState>
    //val details: BehaviorSubject<DetailsResponse>

    /**
     * Network calls from the sdk (without RxJava)
     */
    fun getMovies(page: Int = 1)

    /**
     * Network calls from the sdk using RxJava
     */
    fun getPopularMovies(page: Int = 1): Flow<ResultState<List<DomainMovie>>>

    /**
     * Changed to suspend function because of the Paging implementation
     */
    suspend fun getUpcomingMovies(page: Int = 1): ResultState<List<DomainMovie>>

    /**
     * Unlike getNowPlaying sdk function(that send a broadcast receiver)
     * this one does not return a value IDK why
     */
    suspend fun getNowPlayingMovies(page: Int = 1) : ResultState<List<DomainMovie>>
    fun getMovieDetails(movieId: String): Single<DetailsResponse>

}