package dev.xascar.network_sdk.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import dev.xascar.network_sdk.model.MoviesResponse
import java.lang.Exception

abstract class NowPlayingMoviesBroadcast(
    private val success: (playingNowMovies: MoviesResponse) -> Unit,
    private val error: (error: String) -> Unit,

)  : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        intent?.let{
            val data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializableExtra(MOVIE_NOW_PLAYING, MoviesResponse::class.java)
            } else {
                it.getSerializableExtra(MOVIE_NOW_PLAYING) as MoviesResponse
            }

            val error = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getSerializableExtra(ERROR_NOW_PLAYING, Exception::class.java)
            } else {
                it.getSerializableExtra(ERROR_NOW_PLAYING) as Exception
            }

            data?.let {movieResponse ->
                playingNowMoviesSuccess(movieResponse)
                success(movieResponse)
            }?: let {
                error?.let {
                    playingNowMoviesError("Data is null")
                    error("Data is null")
                }
            }
        } ?: let {
            playingNowMoviesError("RECEIVER WAS NOT REGISTERED")
            error("RECEIVER WAS NOT REGISTERED")
        }



    }

    abstract fun playingNowMoviesSuccess(playingNowMovies: MoviesResponse)
    abstract fun playingNowMoviesError(error: String)

    companion object{
        private const val ACTION = "dev.xascar.network_sdk.utils.NowPlayingMoviesBroadcast"
        private const val MOVIE_NOW_PLAYING = "MOVIE_NOW_PLAYING"
        private const val ERROR_NOW_PLAYING = "ERROR_DETAILS"

        fun getIntent(data: MoviesResponse? = null, error: Exception? = null)
        = Intent(ACTION).apply {
            data?.let {
                putExtra(MOVIE_NOW_PLAYING,data)
                putExtra(ERROR_NOW_PLAYING,error)

            }
        }
    }

}