package com.example.lifecyclesandbox.movie_list.shared

sealed class MovieSharedListState {
    class Success (val movieList : List<MovieListViewModel.MovieUI>) : MovieSharedListState()
    class Failure (val e : Exception) : MovieSharedListState()
    object Loading : MovieSharedListState()
}
