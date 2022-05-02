package com.example.lifecyclesandbox.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclesandbox.R

class PopularMoviesFragment : Fragment(R.layout.popular_movies_fragment) {
    private val viewModel: MovieListViewModel by viewModels(ownerProducer = { this })
    private var recyclerView: RecyclerView? = null
    private val adapter = MoviesAdapter(mutableListOf())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.movieListRecyclerView)
        setAdapter()
        viewModel.popularMovies.observe(viewLifecycleOwner) { state ->
            when (state) {
                // Error
                is MovieListState.Failure -> Toast.makeText(context, "An error occurred.", Toast.LENGTH_SHORT).show()
                // Loading
                MovieListState.Loading -> Unit
                // Success
                is MovieListState.Success -> {
                    adapter.updateMovieList(state.popularMovieList)
                }
            }
        }
    }

    private fun setAdapter() {
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.itemAnimator = DefaultItemAnimator()
        recyclerView?.adapter = adapter
    }
}