package dev.xascar.moviesapppro.repository

import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.moviesapppro.data.model.mapToDomainMovie
import dev.xascar.moviesapppro.rest.MoviesApi
import dev.xascar.moviesapppro.utils.NullResponseException
import dev.xascar.moviesapppro.utils.ResultState
import dev.xascar.moviesapppro.utils.UnsuccessfulResponseException
import dev.xascar.network_sdk.NetworkApi
import dev.xascar.network_sdk.model.details.DetailsResponse
import dev.xascar.network_sdk.utils.DataState
import dev.xascar.network_sdk.utils.MovieCategory
import dev.xascar.network_sdk.utils.toLowerCase
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi,
    private val movieApi: MoviesApi
) : MoviesRepository {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val _movies: MutableStateFlow<DataState> = MutableStateFlow(DataState.LOADING)

    /**
     * [StateFlow] is a Kotlin flow that allows you to have reactive programming that emits
     * a stream of data, it requires a default value and once it start getting values
     * it is gonna be emitting those values, that's why it is considered as a hot flow.
     * Unlike LiveData it only emits different values
     *
     */
    override val movies: StateFlow<DataState>
        get() = _movies

    //private val networkApi = NetworkApiImpl()

    /**
     * Network calls from the sdk
     */
    override fun getMovies(page: Int) {
        coroutineScope.launch {
            val result = networkApi.getUpcoming(page)
            _movies.value = result
        }
    }


    /**
     * Network calls from the sdk (10:30)
     */
    override fun getPopularMovies(page: Int): Flow<ResultState<List<DomainMovie>>> = flow {
        emit(ResultState.Loading)

//        val useCase = PopularMoviesUseCase()
//        useCase(page)

        try {

            val response = movieApi.getMoviesDynamic(
                MovieCategory.POPULAR.toLowerCase(),
                page
            )

            if (response.isSuccessful) {
                response.body()?.let {
                    emit(ResultState.Success(it.results.mapToDomainMovie(MovieCategory.POPULAR)))
                } ?: throw NullResponseException()
            } else {
                throw UnsuccessfulResponseException(
                    response.errorBody()?.string()
                ) //Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(ResultState.Error(e))
        }


    }

    /**
     * Changed to suspend function because of the Paging implementation
     */
    override suspend fun getUpcomingMovies(page: Int): ResultState<List<DomainMovie>> {
        val state = networkApi.getUpcoming(page)

        var result: ResultState<List<DomainMovie>> = ResultState.Loading

        if (state is DataState.SUCCESS) {
            result = ResultState.Success(state.response?.results.mapToDomainMovie(MovieCategory.UPCOMING))
        } else if (state is DataState.ERROR) {
            result = ResultState.Error(state.error)
        }

        return result
    }

    /**
     * Unlike getNowPlaying sdk function(that send a broadcast receiver)
     * this one does not return a value IDK why
     */
    override suspend fun getNowPlayingMovies(page: Int): ResultState<List<DomainMovie>>{
        return try {
            val result = movieApi.getMoviesDynamic(category = MovieCategory.NOW_PLAYING.toLowerCase(),page = page)
            result.body()?.let {
                ResultState.Success(it.results.mapToDomainMovie(MovieCategory.NOW_PLAYING))
            }?: throw Exception("Empty response")
        }catch (e: Exception){
            ResultState.Error(e)
        }
    }

    /**
     *  doOnSuccess/doOnError
     *  Publish/Subject object
     */
    override fun getMovieDetails(movieId: String): Single<DetailsResponse> {
//        return movieApi.getMovieDetailsRxJava(movieId)
//            .doOnSuccess {
//                // For analytics
//                //details.onNext(it)
//
//            }
//            .doOnError { }
        return movieApi.getMovieDetailsRxJava(movieId)
    }


}