package com.example.lifecyclesandbox.movie_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifecyclesandbox.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel : ViewModel() {

    private val _popularMovies = MutableLiveData<MovieListState>()

    val popularMovies: LiveData<MovieListState> = _popularMovies

    /**
     * Load popular movies upon initialization so the movie list can be displayed immediately
     */
    init {
        loadPopularMovies()
    }

    private fun loadPopularMovies() {
        // Fetch movies from TMDb
        viewModelScope.launch {
            try {
                val resultList = withContext(Dispatchers.IO) {
                   MovieApi.retrofitService.getPopularMovies()
                }
                _popularMovies.value = MovieListState.Success(resultList.results)
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _popularMovies.value = MovieListState.Failure(e)
            }
        }
    }

}