package com.example.lifecyclesandbox.movie_list

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.lifecyclesandbox.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import com.example.lifecyclesandbox.movie_detail.MovieDetailActivity
import com.example.lifecyclesandbox.movie_list.model.Movie
import com.example.lifecyclesandbox.network.IMAGE_BASE_URL
import com.squareup.picasso.Picasso

class MoviesAdapter(private val movieList: MutableList<Movie>, private val onMovieListener: OnMovieListener) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitleText: TextView = view.findViewById(R.id.movie_title_text_view)
        val movieOverviewText: TextView = view.findViewById(R.id.movie_overview_text_view)
        val moviePoster: ImageView = view.findViewById(R.id.movie_poster_image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieID = movieList[position].id
        val movieTitle = movieList[position].title
        val movieOverview = movieList[position].overview
        val moviePosterPath = IMAGE_BASE_URL + movieList[position].posterPath

        holder.movieTitleText.text = movieTitle
        holder.movieOverviewText.text = movieOverview
        Picasso.get().load(moviePosterPath).into(holder.moviePoster)

        holder.itemView.setOnClickListener {
            onMovieListener.onMovieClicked(movieID)
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
        fun onMovieClicked(movieID : Long)
    }

}
