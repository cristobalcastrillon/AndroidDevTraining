package com.example.lifecyclesandbox.movie_list

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclesandbox.R
import com.example.lifecyclesandbox.movie_detail.MovieDetailActivity
import com.example.lifecyclesandbox.movie_list.shared.MovieListViewModel
import com.example.lifecyclesandbox.movie_list.shared.MovieSharedListAdapter
import com.example.lifecyclesandbox.movie_list.shared.MovieSharedListState

class FavoriteMovieListFragment : Fragment(R.layout.movie_list_fragment),
    MovieSharedListAdapter.OnMovieListener,
    MovieSharedListAdapter.OnFavListener
{
    // GET Request to TMDb: FavoriteMovieListViewModel version
    // private val viewModelFavorite: FavoriteMovieListViewModel by viewModels()

    // GET Request to TMDb: MovieListViewModel version
    private val viewModelFavorite : MovieListViewModel by activityViewModels()

    private lateinit var recyclerView: RecyclerView
    private val adapter = MovieSharedListAdapter(mutableListOf(), this, this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView
        recyclerView = view.findViewById(R.id.movie_list_recycler_view)
        setAdapter()

        // GET Request to TMDb: FavoriteMovieListViewModel version
        /*
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
        */

        // GET Request to TMDb: MovieListViewModel version
        viewModelFavorite.favoriteMovies.observe(viewLifecycleOwner) { state ->
            when (state) {
                // Error
                is MovieSharedListState.Failure -> Toast.makeText(
                    context,
                    "An error occurred.",
                    Toast.LENGTH_SHORT
                ).show()
                // Loading
                MovieSharedListState.Loading -> Unit
                // Success
                is MovieSharedListState.Success -> {
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

    override fun onFavButtonClicked(movieUI: MovieListViewModel.MovieUI) {
        // TODO("Not yet implemented")
        if(requireView().isSelected){
            // addToFavoriteMovies()
        }
        else{
            // removeFromFavoriteMovies()
        }
    }
}