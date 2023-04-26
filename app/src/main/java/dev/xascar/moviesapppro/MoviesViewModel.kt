package dev.xascar.moviesapppro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.moviesapppro.usecases.PlayingNowUseCase
import dev.xascar.moviesapppro.usecases.PopularMoviesUseCase
import dev.xascar.moviesapppro.utils.ResultState
import dev.xascar.network_sdk.utils.MovieCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MoviesUIState{
    object LOADING: MoviesUIState()
    object SUCCESS: MoviesUIState()
    data class ERROR(val exception: Exception): MoviesUIState()
}


/**
 *  ViewModel
 *      -
 *      - Is a class that handles the business logic
 *      - Request data to the model and send it to the view
 *      - It is designed to persists data through configuration changes
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    /**
     * repository Is not longer needed since we are using clean architecture and repository
     * will be called from defined use cases
     */
    //private val repository: MoviesRepository
    private val popularMoviesUseCase: PopularMoviesUseCase,
    private val playingNowUseCase: PlayingNowUseCase
): ViewModel() {

    /**
     * moviesUIState Is not longer needed since we are using clean architecture and repository
     * will be called from defined use cases
     */
    //val moviesUIState = repository.movies.value

    /**
     * MutableLiveData
     *      - Expose getters and setters for handling its value within the viewModel
     *      - Follows backing property to avoid modifications to its value outside of the class
     *
     */
    private val _popular: MutableLiveData<ResultState<List<DomainMovie>>> = MutableLiveData(ResultState.Loading)

    /**
     * LiveData
     *      - Is a data holder class that is intended to be observed in UI Components such as Activities, Fragments
     *      - It is being observed only on active states such as onStart and onResume
     *      - Expose only
     */
    val popular: LiveData<ResultState<List<DomainMovie>>> = _popular

    /**
     * We can use:
     *      - LiveData
     *      - MutableStates
     *      - *** StateFlows ***
     *      - Flows
     */

    var playingNow: MutableStateFlow<PagingData<DomainMovie>> = MutableStateFlow(PagingData.empty())
        private set

    /**
     * differences between "When" and "switch"
     *      - You can assign it to a variable
     *      - You can define a variable inside of the parenthesis
     *      - No need a break statement
     */
    private fun getMovies(movieCategory: MovieCategory, page: Int){
        when (movieCategory){
            MovieCategory.POPULAR -> getPopular()
            MovieCategory.NOW_PLAYING -> getPlayingNow()
            MovieCategory.UPCOMING -> TODO("Not yet implemented")

        }
    }

    /**
     * Coroutines
     *      - Is a lightweight thread that allows to perform operations within a coroutine scope
     *      - They are created by calling either launch or async functions
     *      - It executes in a background thread
     */

    /**
     * Post value: Assign the value either a background or main thread (whenever is available)
     * Set value: Assign the value in the main thread which means its value is immediately available
     */
    private fun getPopular() {
        viewModelScope.launch {
            popularMoviesUseCase.invoke(1).collect{
                _popular.postValue(it)
            }

        }
    }

    private fun getPlayingNow() {
        viewModelScope.launch {
            playingNowUseCase().collect {
                playingNow.value = it
            }
        }
    }



}