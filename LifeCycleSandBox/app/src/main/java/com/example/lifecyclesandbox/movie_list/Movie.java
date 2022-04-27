package com.example.lifecyclesandbox.movie_list;

public class Movie {
    private String movieTitle;

    public Movie(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
}
