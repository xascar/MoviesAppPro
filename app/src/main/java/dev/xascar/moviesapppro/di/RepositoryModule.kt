package dev.xascar.moviesapppro.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.xascar.moviesapppro.repository.MoviesRepository
import dev.xascar.moviesapppro.repository.MoviesRepositoryImpl
import dev.xascar.network_sdk.NetworkApi
import dev.xascar.network_sdk.NetworkApiImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepo(repository: MoviesRepositoryImpl): MoviesRepository

    @Binds
    abstract fun provideNetworkApi(service: NetworkApiImpl): NetworkApi

}