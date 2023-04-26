package dev.xascar.moviesapppro.database

import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.network_sdk.utils.MovieCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalRepository {
    suspend fun insertMovies(movies: List<DomainMovie>)
    fun getAllMoviesByType(type: MovieCategory): Flow<List<DomainMovie>>
    suspend fun getMovieById(id: Int): DomainMovie
    suspend fun deleteMovies()
}

class LocalRepositoryImpl @Inject constructor(
    private val moviesDao: MoviesDao
) : LocalRepository{

    override suspend fun insertMovies (movies: List<DomainMovie>) =
        moviesDao.insertMovies(movies)

    override fun getAllMoviesByType(type: MovieCategory): Flow<List<DomainMovie>> =
        moviesDao.getAllMoviesByType(type)

    override suspend fun getMovieById(id: Int): DomainMovie =
        moviesDao.getMovieById(id)

    override suspend fun deleteMovies() =
        moviesDao.deleteMovies()

}