package dev.xascar.moviesapppro.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.moviesapppro.paging.MoviesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlayingNowUseCase @Inject constructor(
    private val moviesDataSource: MoviesDataSource
) {

    operator fun invoke(): Flow<PagingData<DomainMovie>> {
        return Pager(PagingConfig(moviesDataSource.totalPages)) {
            moviesDataSource
        }.flow
    }
}