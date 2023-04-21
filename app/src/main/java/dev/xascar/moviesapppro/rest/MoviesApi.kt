package dev.xascar.moviesapppro.rest

import dev.xascar.network_sdk.model.MoviesResponse
import dev.xascar.network_sdk.model.details.DetailsResponse
import io.reactivex.rxjava3.core.Single
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
     * For RxJava implementation:
     */

    @GET(CATEGORY_PATH)
    suspend fun getMoviesDynamic(
        @Path("category") category: String,
        @Query("page") page: Int,
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MoviesResponse>

    @GET(DETAILS_PATH)
    fun getMovieDetailsRxJava(
        @Path("movie_id") id: String, //502356
        @Query("language") language: String = LANGUAGE,
        @Query("api_key") apiKey: String = API_KEY
    ): Single<DetailsResponse>

    companion object{

        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        //https://api.themoviedb.org/3/movie/{movie_id}?api_key=<<api_key>>&language=en-US
        private const val NOW_PLAYING_ENDPOINT = "now_playing"
        private const val UPCOMING_ENDPOINT = "upcoming"
        private const val POPULAR_ENDPOINT = "popular"

        private const val DETAILS_PATH = "{movie_id}"
        private const val CATEGORY_PATH = "{category}"

        private const val API_KEY = "2e7f73a4d6f04aba739e7dc139e2aa78"
        private const val LANGUAGE = "en-EN"



    }
}