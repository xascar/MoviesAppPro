package dev.xascar.network_sdk.di

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.xascar.network_sdk.NetworkApi
import dev.xascar.network_sdk.rest.MoviesApi
import dev.xascar.network_sdk.rest.MoviesApi.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

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
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(MoviesApi::class.java)
        }
        return qatarApi
    }

}