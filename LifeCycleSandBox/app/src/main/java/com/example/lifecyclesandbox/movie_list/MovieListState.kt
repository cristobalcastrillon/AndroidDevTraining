package com.example.lifecyclesandbox.movie_list

sealed class MovieListState {
    class Success (val popularMovieList : List<Movie>) : MovieListState()
    class Failure (val e : Exception) : MovieListState()
    object Loading : MovieListState()
}
