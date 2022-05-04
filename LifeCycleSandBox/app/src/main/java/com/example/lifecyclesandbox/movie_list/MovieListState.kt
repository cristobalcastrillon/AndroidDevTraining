package com.example.lifecyclesandbox.movie_list

import com.example.lifecyclesandbox.movie_list.model.Movie

sealed class MovieListState {
    class Success (val movieList : List<Movie>) : MovieListState()
    class Failure (val e : Exception) : MovieListState()
    object Loading : MovieListState()
}
