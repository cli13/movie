package com.example.movietab.data.remote

import com.example.movietab.data.response.NowPlayingResponse
import com.example.movietab.data.response.PopularResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    companion object {
        const val API_KEY = "35ddd0f68daf34c7e82522b910cc92ac"
        const val BASE_URL = "https://api.themoviedb.org/3/"

//        operator fun invoke(): MovieApi {
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(MovieApi::class.java)
//        }
    }

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US")
    suspend fun getCurrentAiringMovies(@Query("page") page: String): Response<NowPlayingResponse>

    @GET("movie/popular?api_key=$API_KEY&language=en-US")
    suspend fun getPopularMovies(@Query("page") page: String): Response<PopularResponse>

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US")
    fun getRxjavademo(@Query("page") page: String): Single<NowPlayingResponse>
}