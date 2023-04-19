package dev.xascar.moviesapppro.repository

import dev.xascar.moviesapppro.utils.DataState
import dev.xascar.network_sdk.NetworkApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val service: NetworkApi) : MoviesRepository {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val _movies: MutableStateFlow<DataState> = MutableStateFlow(DataState.LOADING)
    override val movies: StateFlow<DataState>
        get() = _movies

    //private val service = NetworkApiImpl()

    override fun getMovies(){
        coroutineScope.launch {
            val response = service.getNowPlaying()
//            if (response.isSuccessful){
//                _movies.value = DataState.SUCCESS(response.body()!!.results)
//
//            }
        }
    }


}