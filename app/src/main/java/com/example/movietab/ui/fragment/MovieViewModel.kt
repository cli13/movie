package com.example.movietab.ui.fragment

import android.util.Log
import androidx.lifecycle.*
import com.example.movietab.data.MainRepository
import com.example.movietab.data.response.MovieResult
import com.example.movietab.data.response.NowPlayingResponse
import com.example.movietab.data.response.PopularResponse
import kotlinx.coroutines.Dispatchers.IO
import retrofit2.Response

class MovieViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val moviePage = MutableLiveData<String>()
    private var _moviePage = 1

    init {
        moviePage.value = _moviePage.toString()
    }

    fun increase() {
        moviePage.value = (++_moviePage).toString()
    }

    fun decrease() {
        moviePage.value = (--_moviePage).toString()
    }

    fun getMoviesForPage() = liveData(IO) {
        var response = mainRepository.getCurrentMovie(moviePage.value!!)
        if (response.isSuccessful) {
            emit(response.body())
        }
    }

    fun getPopularMovieForPage() = liveData(IO) {
        var response = mainRepository.getPopularMovie(moviePage.value!!)
        if (response.isSuccessful) {
            emit(response.body())
        }
    }

}