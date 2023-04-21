package dev.xascar.moviesapppro.repository

import dev.xascar.moviesapppro.domain.MovieDomain
import dev.xascar.moviesapppro.domain.mapToDomainMovie
import dev.xascar.moviesapppro.rest.MoviesApi
import dev.xascar.moviesapppro.usecases.PopularMoviesUseCase
import dev.xascar.moviesapppro.utils.NullResponseException
import dev.xascar.moviesapppro.utils.ResultState
import dev.xascar.moviesapppro.utils.UnsuccessfulResponseException
import dev.xascar.moviesapppro.utils.makeRetrofitCall
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

class MoviesRepositoryImpl @Inject constructor(private val service: NetworkApi,private val localService: MoviesApi) : MoviesRepository {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val _movies: MutableStateFlow<DataState> = MutableStateFlow(DataState.LOADING)
    override val movies: StateFlow<DataState>
        get() = _movies

    //private val service = NetworkApiImpl()

    /**
     * Network calls from the sdk
     */
    override fun getMovies(page: Int){
        coroutineScope.launch {
            val result = service.getUpcoming(page)
            _movies.value = result
        }
    }


    /**
     * Network calls from the sdk
     */
    override fun getPopularMovies(page: Int): Flow<ResultState<List<MovieDomain>>> = flow {
        emit(ResultState.Loading)

//        val useCase = PopularMoviesUseCase()
//        useCase(page)

        try{

            val response =localService.getMoviesDynamic(
                MovieCategory.POPULAR.toLowerCase(),
                page
            )

            if(response.isSuccessful){
                response.body()?.let {
                    emit(ResultState.Success(it.results.mapToDomainMovie()))
                } ?: throw NullResponseException()
            }else{
                throw UnsuccessfulResponseException(response.errorBody()?.string()) //Exception(response.errorBody()?.string())
            }
        }catch (e: Exception){
            emit(ResultState.Error(e))
        }


    }

    override fun getUpcomingMovies(page: Int): Flow<ResultState<List<MovieDomain>>> = flow {
//        emit(
//            makeRetrofitCall
//            {
//                localService.getMoviesDynamic(
//                    MovieCategory.POPULAR.toLowerCase(),
//                    page
//                )
//            }
//
//        )
    }

    override fun getNowPlayingMovies(page: Int): Flow<ResultState<List<MovieDomain>>> {
        TODO("Not yet implemented")
    }

    /**
     *  doOnSuccess/doOnError
     *  Publish/Subject object
     */
    override fun getMovieDetails(movieId: String): Single<DetailsResponse> {
        return localService.getMovieDetailsRxJava(movieId)
            .doOnSuccess {
                // For analytics
                //details.onNext(it)

            }
            .doOnError {  }
    }


}