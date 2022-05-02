package com.example.lifecyclesandbox.movie_list.model

import com.google.gson.annotations.SerializedName

data class Movie(
        @SerializedName("id")
        val id: Long,
        @SerializedName("title")
        val title: String,
        @SerializedName("release_date")
        val releaseDate: String,
        @SerializedName("overview")
        val overview: String,
        @SerializedName("vote_average")
        val voteAverage: Double,
        @SerializedName("poster_path")
        val posterPath: String
)