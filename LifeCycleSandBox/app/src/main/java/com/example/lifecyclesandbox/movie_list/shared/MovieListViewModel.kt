package com.example.lifecyclesandbox.movie_list.shared

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

    // Popular Movies Live Data
    private val _movieListLiveData = MutableLiveData<MovieSharedListState>()

    val movieListLiveData: LiveData<MovieSharedListState> = _movieListLiveData

    fun loadPopularMovies() {
        // Fetch movies from TMDb
        viewModelScope.launch {
            try {
                val popularMoviesResultList = withContext(Dispatchers.IO) {
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
                _movieListLiveData.value = MovieSharedListState.Success(popularMoviesResultList)
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _movieListLiveData.value = MovieSharedListState.Failure(e)
            }
        }
    }

    fun filterFavoriteMovies() {
        // Iterate over favoriteMovieList to find where 'favorite' boolean variable is set 'true'
        val currentList = (movieListLiveData.value as? MovieSharedListState.Success)?.movieList ?: emptyList()
        viewModelScope.launch {
            try {
                val favoriteMovieResultList = if (currentList.isNotEmpty()) {
                     withContext(Dispatchers.IO) {
                        currentList.filter { movie -> movie.favorite }
                    }
                } else emptyList()

                if(favoriteMovieResultList.isEmpty())
                    _movieListLiveData.value = MovieSharedListState.EmptyMovieList
                else
                    _movieListLiveData.value = MovieSharedListState.Success(currentList, favoriteList = favoriteMovieResultList)
            }
            catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _movieListLiveData.value = MovieSharedListState.Failure(e)
            }
        }
    }

    fun updateFavoriteMovie(movieUI: MovieUI) {
        val currentList = (movieListLiveData.value as? MovieSharedListState.Success)?.movieList?.map {
            if(it.id == movieUI.id) movieUI else it
        } ?: emptyList()
        _movieListLiveData.value = MovieSharedListState.Success(
            currentList , favoriteList = currentList.filter { it.favorite }
        )
    }


    data class MovieUI (
        val id: Long,
        val title: String,
        val releaseDate: String,
        val overview: String,
        val popularity: Double,
        val voteAverage: Double,
        val posterPath: String,
        var favorite: Boolean = false
        )
}