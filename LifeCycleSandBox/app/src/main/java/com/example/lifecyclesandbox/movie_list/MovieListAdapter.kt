package com.example.lifecyclesandbox.movie_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclesandbox.R
import com.example.lifecyclesandbox.movie_list.model.Movie
import com.example.lifecyclesandbox.network.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class MovieListAdapter(
    private val movieList: MutableList<Movie>,
    private val onMovieListener: OnMovieListener,
    private val onFavListener: OnFavListener
) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitleText: TextView = view.findViewById(R.id.movie_title_text_view)
        val movieOverviewText: TextView = view.findViewById(R.id.movie_overview_text_view)
        val moviePoster: ImageView = view.findViewById(R.id.movie_poster_image_view)
        val movieRatingText: TextView = view.findViewById(R.id.movie_rating)
        val favButton: ImageButton = view.findViewById(R.id.favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieID = movieList[position].id
        val movieTitle = movieList[position].title
        val movieOverview = movieList[position].overview
        val movieRating = movieList[position].popularity
        val moviePosterPath = IMAGE_BASE_URL + movieList[position].posterPath

        Picasso.get().load(moviePosterPath).into(holder.moviePoster)
        holder.movieTitleText.text = movieTitle
        holder.movieOverviewText.text = movieOverview
        holder.movieRatingText.text = movieRating

        holder.itemView.setOnClickListener {
            onMovieListener.onMovieClicked(movieID)
        }

        holder.favButton.setOnClickListener {
            onFavListener.onFavButtonClicked(movieID)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovieList(popularMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(popularMovieList)
        notifyDataSetChanged()
    }

    interface OnMovieListener {
        fun onMovieClicked(movieID: Long)
    }

    interface OnFavListener {
        fun onFavButtonClicked(movieID: Long)
    }

}
