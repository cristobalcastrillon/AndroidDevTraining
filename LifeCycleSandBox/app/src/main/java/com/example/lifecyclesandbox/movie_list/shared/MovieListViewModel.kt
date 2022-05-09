package com.example.lifecyclesandbox.movie_list.shared

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifecyclesandbox.db.LocalDataSource
import com.example.lifecyclesandbox.db.MovieData
import com.example.lifecyclesandbox.movie_list.shared.MovieSharedListState.Success
import com.example.lifecyclesandbox.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListViewModel : ViewModel() {

    // Popular Movies Live Data
    private val _movieListLiveData = MutableLiveData<MovieSharedListState>()

    val movieListLiveData: LiveData<MovieSharedListState> = _movieListLiveData

    /**
     * Function that makes a GET request to TMDb Web API, and maps incoming object from JSON to Movie and subsequently to MovieUI.
     */
    fun loadPopularMovies() {
        // Fetch movies from TMDb
        viewModelScope.launch {
            try {
                val popularMoviesResultList : List<MovieUI>
                 withContext(Dispatchers.IO) {

                    val favoriteMoviesResultList = LocalDataSource().db.movieDAO().loadAllFavorites().map {
                        mapFromDBModel(it)
                    }
                    val favIDs = favoriteMoviesResultList.map{
                         favMovie -> favMovie.id
                    }

                    popularMoviesResultList = MovieApi.retrofitService.getPopularMovies().results.map {
                        MovieUI(
                            it.id,
                            it.title,
                            it.releaseDate,
                            it.overview,
                            it.popularity,
                            it.voteAverage,
                            it.posterPath,
                            favIDs.contains(it.id)
                        )
                    }

                    mergeLists(favoriteMoviesResultList, popularMoviesResultList)
                }
                _movieListLiveData.value = Success(popularMoviesResultList, emptyList())
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _movieListLiveData.value = MovieSharedListState.Failure(e)
            }
        }
    }

    /**
     * Favorite movies should be retrieved from local DB.
     */
    fun loadFavoriteMovies() {
        val popularMovieList = (movieListLiveData.value as? Success)?.movieList!!
        viewModelScope.launch {
            try {
                val favoriteMovieResultList = withContext(Dispatchers.IO) {
                    LocalDataSource().db.movieDAO().loadAllFavorites().map {
                        mapFromDBModel(it)
                    }
                }
                _movieListLiveData.value =
                    Success(popularMovieList, favoriteList = favoriteMovieResultList)
            } catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _movieListLiveData.value = MovieSharedListState.Failure(e)
            }
        }
    }

    /**
     * Movies tagged as favorite should be saved to the local DB,
     * in order to be retrieved whenever the user switches to the FavoriteMovieListFragment,
     * as well as when the parent MovieListActivity ceases to exist.
     */
    fun updateFavoriteMovie(movieUI: MovieUI) {

        var favoriteMoviesResultList: MutableList<MovieUI> = mutableListOf()
        var popularMoviesResultList: List<MovieUI> = emptyList()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // Writing to the database if favorite is true (user wants to add it to favorites)
                if (movieUI.favorite) {

                    // Delete Movie with the same ID in Local DB
                    LocalDataSource().db.movieDAO().deleteByID(movieUI.id)
                    // Insert new MovieUI
                    LocalDataSource().db.movieDAO().insertAll(
                        movieUI.mapToDBModel()
                    )

                    favoriteMoviesResultList =
                        (movieListLiveData.value as? Success)?.favoriteList?.toMutableList()
                            ?: mutableListOf()
                    popularMoviesResultList =
                        (movieListLiveData.value as? Success)?.movieList?.map {
                            if (it.id == movieUI.id) {
                                favoriteMoviesResultList.add(movieUI)
                                movieUI
                            } else it
                        } ?: emptyList()
                }
                // Deleting favorite from the database if favorite is false (user wants to remove it from favorites)
                else {
                    // Delete Movie with the same ID in Local DB
                    LocalDataSource().db.movieDAO().deleteByID(movieUI.id)

                    favoriteMoviesResultList =
                        (movieListLiveData.value as? Success)?.favoriteList?.toMutableList()
                            ?: mutableListOf()
                    popularMoviesResultList =
                        (movieListLiveData.value as? Success)?.movieList?.map {
                            if (it.id == movieUI.id) {
                                favoriteMoviesResultList.remove(movieUI)
                                movieUI
                            } else it
                        } ?: emptyList()
                }
            }
            _movieListLiveData.value = Success(
                popularMoviesResultList, favoriteList = favoriteMoviesResultList
            )
        }
    }

    /**
     * Helper Data Class which holds data to be shown on the GUI.
     */
    data class MovieUI(
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

// MovieUI Extension Function
fun MovieListViewModel.MovieUI.mapToDBModel(): MovieData {
    return MovieData(
        id,
        title,
        releaseDate,
        overview,
        popularity,
        voteAverage,
        posterPath,
        favorite
    )
}

fun mapFromDBModel(movieData: MovieData): MovieListViewModel.MovieUI {
    return MovieListViewModel.MovieUI(
        movieData.id,
        movieData.title,
        movieData.releaseDate,
        movieData.overview,
        movieData.popularity,
        movieData.voteAverage,
        movieData.posterPath,
        movieData.favorite
    )
}

fun mergeLists(popularMoviesResultList: List<MovieListViewModel.MovieUI>, favoriteMoviesResultList: List<MovieListViewModel.MovieUI>) {

}