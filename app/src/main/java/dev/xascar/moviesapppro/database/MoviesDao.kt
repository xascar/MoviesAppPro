package dev.xascar.moviesapppro.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.xascar.moviesapppro.data.model.DomainMovie
import dev.xascar.network_sdk.utils.MovieCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies (movies: List<DomainMovie>)

    @Query ("SELECT * FROM movies_table where movieTypes LIKE :type")
    fun getAllMoviesByType(type: MovieCategory) : Flow<List<DomainMovie>>

    @Query("SELECT * FROM movies_table where id = :id")
    suspend fun getMovieById(id: Int) : DomainMovie

    //@Delete
    @Query("Delete from movies_table")
    suspend fun deleteMovies()

}