package com.example.lifecyclesandbox.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclesandbox.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PopularMoviesFragment : Fragment(R.layout.popular_movies_fragment){

    // TODO: Move getPopularMovieRequestURL and movieList to another class that will handle TMDB API IO
    private val getPopularMoviesRequestURL: String by lazy { getString(R.string.tmdb_get_popular_movies) }
    private val movieList = arrayMovieList()
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.movieListRecyclerView)
        setAdapter()
    }

    private fun setAdapter() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val adapter = MoviesAdapter(movieList)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
                recyclerView?.layoutManager = layoutManager
                recyclerView?.itemAnimator = DefaultItemAnimator()
                recyclerView?.adapter = adapter
            }
        }
    }

    private fun arrayMovieList(): ArrayList<Movie> {
        // TODO: Get list from API and parse
        val movieList = ArrayList<Movie>()
        movieList.add(Movie("Star Wars"))
        movieList.add(Movie("El Padrino"))
        movieList.add(Movie("The Matrix"))
        return movieList
    }

    private fun getPopularMovies(): ArrayList<Movie>? {
        // TODO: fetch data from the API
        return null
    }


}