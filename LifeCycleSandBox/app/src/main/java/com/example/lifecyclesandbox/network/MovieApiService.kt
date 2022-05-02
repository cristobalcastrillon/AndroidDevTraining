package com.example.lifecyclesandbox.network

import com.example.lifecyclesandbox.movie_list.model.TMDbResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "f70b04499b4fa4ac8d914250b96ac550"

private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String = API_KEY): TMDbResponse
}

object MovieApi {
    val retrofitService: MovieApiService =
        retrofit.create(MovieApiService::class.java)
}