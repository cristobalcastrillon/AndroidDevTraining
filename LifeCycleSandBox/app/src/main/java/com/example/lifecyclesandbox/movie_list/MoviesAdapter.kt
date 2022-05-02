package com.example.lifecyclesandbox.movie_list

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.lifecyclesandbox.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.lifecyclesandbox.movie_list.model.Movie
import com.squareup.picasso.Picasso

class MoviesAdapter(private val movieList: MutableList<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitleText: TextView = view.findViewById(R.id.movie_title_textView)
        val movieOverviewText: TextView = view.findViewById(R.id.movie_overview_textView)
        val moviePoster: ImageView = view.findViewById(R.id.movie_poster_imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieTitle = movieList[position].title
        val movieOverview = movieList[position].overview

        val moviePosterPath = "https://image.tmdb.org/t/p/original/" + movieList[position].posterPath
        // val moviePosterPath = "https://images.unsplash.com/photo-1616530940355-351fabd9524b?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1935&q=80"

        holder.movieTitleText.text = movieTitle
        holder.movieOverviewText.text = movieOverview
        Picasso.get().load(moviePosterPath).into(holder.moviePoster)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateMovieList(popularMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(popularMovieList)
        notifyDataSetChanged()
    }
}