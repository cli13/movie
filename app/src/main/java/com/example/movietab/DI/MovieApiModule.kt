package com.example.movietab.DI

import com.example.movietab.data.MainRepository
import com.example.movietab.data.remote.MovieApi
import com.example.movietab.ui.fragment.MovieViewModelFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MovieApiModule {
    @Provides
    @Singleton
    fun provideInstance(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMainRepository(movieApiClient: MovieApi): MainRepository {
        return MainRepository(movieApiClient)
    }

    @Provides
    fun provideMovieFactory(repo: MainRepository): MovieViewModelFactory {
        return MovieViewModelFactory(repo)
    }
}