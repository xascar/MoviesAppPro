package dev.xascar.network_sdk.rest

import dev.xascar.network_sdk.model.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi{
    // URL, Retrofit, Interface
    @GET(NOW_PLAYING)
     suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE
     ): Response<MoviesResponse>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE): Response<MoviesResponse>

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int = PAGE): Response<MoviesResponse>

     //todo validate use of same endpoint
     @GET(ID)
     suspend fun getMovieDetails(
        @Path(ID) id: Int = 502356,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE): Response<MoviesResponse>

    companion object{

        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        //https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
        private const val NOW_PLAYING = "now_playing"
        private const val UPCOMING = "upcoming"
        private const val POPULAR = "popular"

        private const val ID = "{movie_id}"

        private const val API_KEY = "2e7f73a4d6f04aba739e7dc139e2aa78"
        private const val LANGUAGE = "en-EN"
        private const val PAGE = 1



    }
}