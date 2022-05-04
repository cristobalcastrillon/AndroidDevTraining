package com.example.lifecyclesandbox.movie_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifecyclesandbox.movie_list.shared.MovieSharedListState
import com.example.lifecyclesandbox.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel : ViewModel() {

    private val _movies = MutableLiveData<MovieSharedListState>()

    val movies: LiveData<MovieSharedListState> = _movies

    /**
     * Load popular movies upon initialization so the movie list can be displayed immediately
     */
    init {
        loadMovies()
    }

    private fun loadMovies() {
        // Fetch movies from TMDb
        viewModelScope.launch {
            try {
                val resultList = withContext(Dispatchers.IO) {
                    MovieApi.retrofitService.getPopularMovies().results.map {
                        MovieUI(it.id,
                        it.title,
                        it.releaseDate,
                        it.overview,
                        it.popularity,
                        it.voteAverage,
                        it.posterPath)
                    }
                }
                _movies.value = MovieSharedListState.Success(resultList)
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _movies.value = MovieSharedListState.Failure(e)
            }
        }
    }

    class MovieUI (
        val id: Long,
        val title: String,
        val releaseDate: String,
        val overview: String,
        val popularity: String,
        val voteAverage: Double,
        val posterPath: String,
        var favorite: Boolean = false
        )

}