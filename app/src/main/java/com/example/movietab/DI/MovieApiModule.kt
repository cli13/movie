package com.example.movietab.DI

import com.example.movietab.data.MainRepository
import com.example.movietab.data.remote.MovieApi
import com.example.movietab.ui.fragment.MovieViewModelFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class MovieApiModule {
    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(MovieApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

//    @Singleton
//    @Provides
//    fun provideMainRepository(movieApiClient: MovieApi): MainRepository {
//        return MainRepository(movieApiClient)
//    }
//
//    @Provides
//    fun provideMovieFactory(repo: MainRepository): MovieViewModelFactory {
//        return MovieViewModelFactory(repo)
//    }
}