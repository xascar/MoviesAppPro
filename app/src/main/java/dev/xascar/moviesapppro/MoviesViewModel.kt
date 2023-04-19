package dev.xascar.moviesapppro

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.xascar.moviesapppro.repository.MoviesRepository
import java.lang.Exception
import javax.inject.Inject

sealed class MoviesUIState{
    object LOADING: MoviesUIState()
    object SUCCESS: MoviesUIState()
    data class ERROR(val exception: Exception): MoviesUIState()
}

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
): ViewModel() {

    private val _moviesUIState : MutableState<MoviesUIState> = mutableStateOf(MoviesUIState.LOADING)
    val moviesUIState = repository.movies.value

    init {
        getMovies()
    }

    private fun getMovies(){

        repository.getMovies()

    }

}