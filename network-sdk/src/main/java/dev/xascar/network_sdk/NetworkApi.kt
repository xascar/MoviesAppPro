package dev.xascar.network_sdk

import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.model.Result
import dev.xascar.network_sdk.rest.MoviesApi
import retrofit2.Response
import javax.inject.Inject

interface NetworkApi {
    fun makeNetworkCall()
    fun modifyOkHttp()
    fun modifyGson()
    suspend fun getNowPlaying(): Response<MoviesResponse>
    suspend fun getUpcoming(): Response<MoviesResponse>
    suspend fun getPopular(): Response<MoviesResponse>
    suspend fun getMovieDetails(id: Int): Response<MoviesResponse>
}

class NetworkApiImpl @Inject constructor(private val moviesApi: MoviesApi): NetworkApi{
    override fun makeNetworkCall(){
        TODO("Not yet implemented")
    }

    override fun modifyOkHttp(){
        TODO("Not yet implemented")
    }

    override fun modifyGson(){
        TODO("Not yet implemented")
    }

    override suspend fun getNowPlaying(): Response<MoviesResponse> {
        return moviesApi.getNowPlaying()
    }

    override suspend fun getUpcoming(): Response<MoviesResponse> {
        return moviesApi.getUpcomingMovies()
    }

    override suspend fun getPopular(): Response<MoviesResponse> {
        return moviesApi.getPopularMovies()
    }

    override suspend fun getMovieDetails(id: Int): Response<MoviesResponse> {
        return moviesApi.getMovieDetails(id)
    }

}