package dev.xascar.moviesapppro.di


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.xascar.moviesapppro.rest.MoviesApi
import dev.xascar.moviesapppro.rest.MoviesApi.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class LocalRepositoryModule  {

    @Provides
    fun provideFootBallAPI(): MoviesApi{
        val qatarApi: MoviesApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(
                    OkHttpClient.Builder().addInterceptor(
                        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                    ).build()
                )
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
        return qatarApi
    }

}