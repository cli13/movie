package com.example.movietab.DI

import com.example.movietab.ui.fragment.MovieFragment
import com.example.movietab.ui.fragment.MovieViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Component(modules = [MovieApiModule::class])
@Singleton
interface ApiComponent {
    fun injectMovieFactoryViewModel(fragment: MovieFragment)
    fun getMovieFactoryViewModel(): MovieViewModelFactory
}