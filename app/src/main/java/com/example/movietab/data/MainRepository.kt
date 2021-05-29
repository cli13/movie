package com.example.movietab.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movietab.data.remote.MovieApi
import com.example.movietab.data.response.NowPlayingResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainRepository @Inject constructor(private val movieApi: MovieApi) {
    suspend fun getCurrentMovie(page: String) = movieApi.getCurrentAiringMovies(page)
    suspend fun getPopularMovie(page: String) = movieApi.getPopularMovies(page)


    fun fetchNowPlaying(page: String): MutableLiveData<NowPlayingResponse> {
        var response = MutableLiveData<NowPlayingResponse>()

        movieApi.getRxjavademo(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<NowPlayingResponse>() {
                override fun onSuccess(t: NowPlayingResponse) {
                    response.value = t
                }

                override fun onError(e: Throwable) {
                    Log.d("rxjava", "onError: ")
                }
            })

        return response
    }
}