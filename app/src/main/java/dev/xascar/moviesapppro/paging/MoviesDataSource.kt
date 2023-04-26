package dev.xascar.moviesapppro.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.moviesapppro.repository.MoviesRepository
import dev.xascar.moviesapppro.utils.ResultState
import javax.inject.Inject

/**
 * This class becomes as if it were a use case so we need to pass the repository to get the data
 * [PagingSource] allows to handle paging by passing the total number of pages
 */
class MoviesDataSource @Inject constructor(
    private val repository: MoviesRepository):
    PagingSource<Int, DomainMovie>() {

    var totalPages: Int = 1
        private set

    /**
     *
     */
    override fun getRefreshKey(state: PagingState<Int, DomainMovie>): Int? {
        TODO("Not yet implemented")
    }

    /**
     * This override function is already a suspend function so it is possible to call it from a coroutine scope
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DomainMovie> {
        return try {
            val nextPage = params.key ?: 1
            when(val state = repository.getNowPlayingMovies(nextPage)) {
                is ResultState.Loading -> LoadResult.Invalid()
                is ResultState.Success -> {
                    totalPages = state.totalPages ?: 1
                    LoadResult.Page(
                        data = state.response,
                        prevKey = if (nextPage == 1) null else nextPage - 1,
                        nextKey = state.page?.plus(1)
                    )
                }
                is ResultState.Error -> LoadResult.Error(state.error)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}
