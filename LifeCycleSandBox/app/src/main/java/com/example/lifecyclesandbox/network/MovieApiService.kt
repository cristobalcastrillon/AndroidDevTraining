package com.example.lifecyclesandbox.network

import com.example.lifecyclesandbox.movie_detail.model.MovieDetail
import com.example.lifecyclesandbox.movie_list.model.PopularMovieListAPIResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "f70b04499b4fa4ac8d914250b96ac550"

private val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build()

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey : String = API_KEY): PopularMovieListAPIResponse

    @GET("movie/{movie_id}")
    suspend fun getDetail(@Path("movie_id") movieID : String,  @Query("api_key") apiKey : String = API_KEY) : MovieDetail
}

object MovieApi {
    val retrofitService: MovieApiService =
        retrofit.create(MovieApiService::class.java)
}