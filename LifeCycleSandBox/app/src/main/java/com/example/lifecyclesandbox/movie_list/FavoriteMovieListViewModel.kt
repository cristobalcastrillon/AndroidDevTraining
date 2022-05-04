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

class FavoriteMovieListViewModel : ViewModel() {

    private val _favoriteMovies = MutableLiveData<MovieListState>()

    val favoriteMovies: LiveData<MovieListState> = _favoriteMovies

    /**
     * Load popular movies upon initialization so the movie list can be displayed immediately
     */
    init {
        loadFavoriteMovies()
    }

    private fun loadFavoriteMovies() {
        // Fetch movies from TMDb
        viewModelScope.launch {
            try {
                val resultList = withContext(Dispatchers.IO) {
                   MovieApi.retrofitService.getFavoriteMovies()
                }
                _favoriteMovies.value = MovieListState.Success(resultList.results)
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _favoriteMovies.value = MovieListState.Failure(e)
            }
        }
    }

}