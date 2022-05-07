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
    private val _popularMovies = MutableLiveData<MovieSharedListState>()

    val popularMovies: LiveData<MovieSharedListState> = _popularMovies

    // Favorite Movies Live Data
    private val _favoriteMovies = MutableLiveData<MovieSharedListState>()

    val favoriteMovies: LiveData<MovieSharedListState> = _favoriteMovies

    // PopularMovieList data which will be shared for the fragments' LiveData observers
    private lateinit var popularMoviesResultList: List<MovieUI>

    /**
     * Load popular movies upon initialization so the movie list can be displayed immediately
     */
    init {
        loadPopularMovies()
        //loadFavoriteMovies()
    }

    private fun loadPopularMovies() {
        // Fetch movies from TMDb
        viewModelScope.launch {
            try {
                popularMoviesResultList = withContext(Dispatchers.IO) {
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
                _popularMovies.value = MovieSharedListState.Success(popularMoviesResultList)
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _popularMovies.value = MovieSharedListState.Failure(e)
            }
        }
    }

    private fun loadFavoriteMovies() {
        // Iterate over favoriteMovieList to find where 'favorite' boolean variable is set 'true'
        viewModelScope.launch {
            try {
                val favoriteMovieResultList = withContext(Dispatchers.IO) {
                    lateinit var tempList : ArrayList<MovieUI>
                    for(movie in popularMoviesResultList) {
                        if(movie.favorite)
                            tempList.add(movie)
                    }
                    tempList
                }
                _favoriteMovies.value = MovieSharedListState.Success(favoriteMovieResultList)
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _popularMovies.value = MovieSharedListState.Failure(e)
            }
        }
    }


    data class MovieUI (
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