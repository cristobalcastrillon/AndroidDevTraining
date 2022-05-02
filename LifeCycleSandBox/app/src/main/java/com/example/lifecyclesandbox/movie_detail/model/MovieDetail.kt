package com.example.lifecyclesandbox.movie_detail.model

import com.google.gson.annotations.SerializedName

data class MovieDetail (
    @SerializedName("title")
    val title : String,
    @SerializedName("original_title")
    val originalTitle : String,
    @SerializedName("original_language")
    val originalLanguage : String,
    @SerializedName("overview")
    val overview : String,
    @SerializedName("popularity")
    val popularity : Number,
    @SerializedName("backdrop_path")
    val backdropPath : String,
    @SerializedName("poster_path")
    val posterPath : String
    )