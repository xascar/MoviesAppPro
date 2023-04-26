package dev.xascar.network_sdk

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import dev.xascar.network_sdk.rest.MoviesApi
import dev.xascar.network_sdk.utils.DataState
import dev.xascar.network_sdk.utils.MovieDetailsCallback
import dev.xascar.network_sdk.utils.NowPlayingMoviesBroadcast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface NetworkApi {

    fun init(context: Context)
    fun makeNetworkCall()
    fun modifyOkHttp()
    fun modifyGson()

    fun getPopular(page: Int): Flow<DataState>
    suspend fun getUpcoming(page: Int): DataState
    /**
     *  The response will be returned in the broadcast receiver
     *  NowPlaying Movies receiver
     */
    fun getNowPlaying(page: Int)//todo min 55
    fun getMovieDetails(id: Int, movieDetails: MovieDetailsCallback)

    fun checkNetworkState(): Boolean
}

class NetworkApiImpl @Inject constructor(): NetworkApi, CoroutineScope by CoroutineScope(Dispatchers.IO) {

    @Inject
    lateinit var moviesApi: MoviesApi

    lateinit var context: Context

    private var isInitialized: Boolean = false
    override fun init(context: Context) {
        this.context = context
        isInitialized = true
    }

    override fun makeNetworkCall(){
        TODO("Not yet implemented")
    }

    override fun modifyOkHttp(){
        TODO("Not yet implemented")
    }

    override fun modifyGson(){
        TODO("Not yet implemented")
    }


    override fun getPopular(page: Int): Flow<DataState> = flow {
        emit(DataState.LOADING)
        try {
            val response = moviesApi.getPopularMovies(page = page)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(DataState.SUCCESS(it))
                } ?: throw Exception("Empty response")
            }else{
                throw Exception(response.errorBody()?.string())
            }
        }catch (e: Exception){
            emit(DataState.ERROR(e))
        }
    }
    override suspend fun getUpcoming(page: Int): DataState {
        return try {
            val response = moviesApi.getUpcomingMovies(page = page)
            if (response.isSuccessful){
                response.body()?.let {
                    Log.d("TAG", "getUpcoming(Success): ${it.results}")
                    DataState.SUCCESS(it)
                } ?: throw Exception("Empty response")
            }else{
                Log.d("TAG", "getUpcoming(Error): ${response.errorBody()?.string()}")
                throw Exception(response.errorBody()?.string())
            }
        }catch (e: Exception){
            DataState.ERROR(e)
        }
    }
    override fun getNowPlaying(page: Int) {
        if (isInitialized){

            launch {

                try {
                    val response = moviesApi.getNowPlaying(page = page)
                    if (response.isSuccessful){
                        response.body()?.let {
                            context.sendBroadcast(NowPlayingMoviesBroadcast.getIntent(data = it))

                        } ?: throw Exception("Empty response")
                    }else{
                        throw Exception(response.errorBody()?.string())
                    }
                }catch (e: Exception){
                    context.sendBroadcast(NowPlayingMoviesBroadcast.getIntent(error = e))

                }
            }

        }


    }
    override fun getMovieDetails(id: Int, movieDetails: MovieDetailsCallback){
        launch {

            try {
                val response = moviesApi.getMovieDetails(id = id)
                if (response.isSuccessful){
                    response.body()?.let {
                        movieDetails.onSuccess(it)
                    } ?: throw Exception("Empty response")
                }else{
                    throw Exception(response.errorBody()?.string())
                }
            }catch (e: Exception){
                movieDetails.onError(e)
            }
        }
    }

    override fun checkNetworkState(): Boolean {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return manager.activeNetwork?.let {
            manager.getNetworkCapabilities(it)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } ?: false
    }

}