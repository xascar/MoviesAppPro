package dev.xascar.network_sdk.rest

import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.model.details.DetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @GET - Defines the endpoint
 * @PATH - Defines the path to be passed between curly braces
 * @QUERY - Used to set the params if needed,
 *          at first query it creates the question mark automatically
 *          on following params it adds the ampersand for identify them
 */

interface MoviesApi{
    // URL, Retrofit, Interface
    /**
     * First implementation, verbose
     */
    @GET(NOW_PLAYING_ENDPOINT)
     suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
     ): Response<MoviesResponse>

    @GET(UPCOMING_ENDPOINT)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @GET(POPULAR_ENDPOINT)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("page") page: Int
    ): Response<MoviesResponse>


     @GET(DETAILS_PATH)
     suspend fun getMovieDetails(
         @Path("movie_id") id: Int,
         @Query("api_key") apiKey: String = API_KEY,
         @Query("language") language: String = LANGUAGE): Response<DetailsResponse>


    companion object{

        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        //https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
        private const val NOW_PLAYING_ENDPOINT = "now_playing"
        private const val UPCOMING_ENDPOINT = "upcoming"
        private const val POPULAR_ENDPOINT = "popular"

        private const val DETAILS_PATH = "{movie_id}"

        private const val API_KEY = "2e7f73a4d6f04aba739e7dc139e2aa78"
        private const val LANGUAGE = "en-EN"



    }
}