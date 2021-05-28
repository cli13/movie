package com.example.movietab.data

import com.example.movietab.data.remote.MovieApi

class MainRepository(private val movieApi: MovieApi) {
    suspend fun getCurrentMovie(page: String) = movieApi.getCurrentAiringMovies(page)
    suspend fun getPopularMovie(page: String) = movieApi.getPopularMovies(page)
}