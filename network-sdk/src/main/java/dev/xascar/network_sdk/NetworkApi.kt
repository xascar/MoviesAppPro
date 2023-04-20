package dev.xascar.network_sdk

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.core.content.ContextCompat.registerReceiver
import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.model.Result
import dev.xascar.network_sdk.rest.MoviesApi
import dev.xascar.network_sdk.utils.DataState
import dev.xascar.network_sdk.utils.MovieDetailsCallback
import dev.xascar.network_sdk.utils.NowPlayingMoviesBroadcast
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
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
    fun getNowPlaying()//todo min 55
    fun getMovieDetails(id: Int, movieDetails: MovieDetailsCallback)
}

class NetworkApiImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val coroutineScope: CoroutineScope = CoroutineScope(ioDispatcher)
): NetworkApi, CoroutineScope by coroutineScope{

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
                    emit(DataState.SUCCESS(it.results))
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
                    DataState.SUCCESS(it.results)
                } ?: throw Exception("Empty response")
            }else{
                throw Exception(response.errorBody()?.string())
            }
        }catch (e: Exception){
            DataState.ERROR(e)
        }
    }
    override fun getNowPlaying() {
        if (isInitialized){

            launch {

                try {
                    val response = moviesApi.getNowPlaying()
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

}