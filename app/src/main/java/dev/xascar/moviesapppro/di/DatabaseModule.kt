package dev.xascar.moviesapppro.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.xascar.moviesapppro.database.MoviesDao
import dev.xascar.moviesapppro.database.MoviesDatabase

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun providesMovieDatabase(@ApplicationContext context: Context): MoviesDatabase {
        val database: MoviesDatabase by lazy {
            MoviesDatabase.getDatabase(context)
        }
        return database
    }

    @Provides
    fun provideMatchesDao(database: MoviesDatabase): MoviesDao {
        return database.getMoviesDao()
    }
}