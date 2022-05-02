package com.example.lifecyclesandbox.movie_detail

import com.example.lifecyclesandbox.movie_detail.model.MovieDetail

sealed class MovieDetailState {
    class Success (val movieDetail : MovieDetail) : MovieDetailState()
    class Failure (val e : Exception) : MovieDetailState()
    object Loading : MovieDetailState()
}
