package com.example.lifecyclesandbox.movie_detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lifecyclesandbox.network.IMAGE_BASE_URL
import com.example.lifecyclesandbox.network.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel : ViewModel() {

    private val _movieDetail = MutableLiveData<MovieDetailState>()
    val movieDetail : LiveData<MovieDetailState> = _movieDetail

    fun loadMovieDetail(movieID: String) {
        viewModelScope.launch {
            try {
                val movieDetail = withContext(Dispatchers.IO) {
                    val movieDetail = MovieApi.retrofitService.getDetail(movieID)
                    val backdropPath = movieDetail.backdropPath
                    movieDetail.copy(backdropPath = "$IMAGE_BASE_URL$backdropPath")
                }
                _movieDetail.value = MovieDetailState.Success(movieDetail)
            }
            catch (e: Exception) {
                Log.e("Failure", e.stackTraceToString())
                _movieDetail.value = MovieDetailState.Failure(e)
            }
        }
    }
}