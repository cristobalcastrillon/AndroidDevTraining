package com.example.lifecyclesandbox.movie_detail

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.lifecyclesandbox.R
import com.squareup.picasso.Picasso

class MovieDetailFragment : Fragment(R.layout.movie_detail_fragment) {

    private val movieDetailViewModel : MovieDetailViewModel by activityViewModels()
    private lateinit var movieTitle : TextView
    private lateinit var movieBackdropImage : ImageView
    private lateinit var movieOverview : TextView
    private var movieID : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieID = arguments?.getString("id")
        movieID?.let { movieDetailViewModel.loadMovieDetail(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieTitle = view.findViewById(R.id.movie_detail_movie_title)
        movieBackdropImage = view.findViewById(R.id.movie_detail_backdrop_image_view)
        movieOverview = view.findViewById(R.id.movie_detail_movie_overview)

        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner) { state ->
            when(state) {
                // Error
                is MovieDetailState.Failure -> Toast.makeText(context, "An error occurred.", Toast.LENGTH_SHORT).show()
                // Success
                is MovieDetailState.Success -> {
                    movieTitle.text = state.movieDetail.title
                    movieOverview.text = state.movieDetail.overview
                    Picasso.get().load(state.movieDetail.backdropPath).into(movieBackdropImage)
                }
                // Loading
                is MovieDetailState.Loading -> Unit
            }
        }
    }
}