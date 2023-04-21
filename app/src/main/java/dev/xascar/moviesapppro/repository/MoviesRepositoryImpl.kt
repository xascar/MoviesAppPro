package dev.xascar.moviesapppro.repository

import dev.xascar.network_sdk.NetworkApi
import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val service: NetworkApi) : MoviesRepository {

    private val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val _movies: MutableStateFlow<DataState> = MutableStateFlow(DataState.LOADING)
    override val movies: StateFlow<DataState>
        get() = _movies

    //private val service = NetworkApiImpl()

    override fun getMovies(page: Int){
        coroutineScope.launch {
            val result = service.getUpcoming(page)
            _movies.value = result
        }
    }

    override fun getPopularMovies(page: Int): Flow<StateFlow<DataState>> = flow {
        when(val state = service.getPopular(page)){
            is DataState ->{

            }

        }
    }

    override fun getUpcomingMovies(): StateFlow<DataState> {
        TODO("Not yet implemented")
    }

    override fun getNowPlayingMovies(): StateFlow<DataState> {
        TODO("Not yet implemented")
    }

    override fun getMovieDetails(): Flow<DataState> {
        TODO("Not yet implemented")
    }


}