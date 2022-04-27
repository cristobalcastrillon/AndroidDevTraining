package com.example.lifecyclesandbox.movie_list

import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.example.lifecyclesandbox.R
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import java.util.ArrayList

class MoviesAdapter(private val movieList: ArrayList<Movie>) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val movieTitleText: TextView = view.findViewById(R.id.movie_title_textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movieTitle = movieList[position].movieTitle
        holder.movieTitleText.text = movieTitle
    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}