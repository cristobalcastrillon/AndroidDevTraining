package com.example.lifecyclesandbox.movie_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclesandbox.R
import com.example.lifecyclesandbox.movie_detail.MovieDetailActivity

class FavoriteMovieListFragment : Fragment(R.layout.movie_list_fragment),
    MovieListAdapter.OnMovieListener {
    private val viewModelFavorite: FavoriteMovieListViewModel by viewModels()
    private lateinit var recyclerView: RecyclerView
    private val adapter = MovieListAdapter(mutableListOf(), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView
        recyclerView = view.findViewById(R.id.movie_list_recycler_view)
        setAdapter()

        viewModelFavorite.favoriteMovies.observe(viewLifecycleOwner) { state ->
            when (state) {
                // Error
                is MovieListState.Failure -> Toast.makeText(
                    context,
                    "An error occurred.",
                    Toast.LENGTH_SHORT
                ).show()
                // Loading
                MovieListState.Loading -> Unit
                // Success
                is MovieListState.Success -> {
                    adapter.updateMovieList(state.movieList)
                }
            }
        }
    }

    private fun setAdapter() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.adapter = adapter
    }

    override fun onMovieClicked(movieID: Long) {
        val movieDetailIntent = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(Intent.EXTRA_TEXT, movieID.toString())
        }
        startActivity(movieDetailIntent)
    }
}