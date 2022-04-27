package com.example.lifecyclesandbox.movie_list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lifecyclesandbox.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListActivity : AppCompatActivity() {
    private val movieList = arrayMovieList()
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)
        recyclerView = findViewById(R.id.movieListRecyclerView)
        Log.d(TAG, "onCreate(): " + MovieListActivity::class.java.canonicalName)
        setAdapter()
    }

    private fun setAdapter() {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                val adapter = MoviesAdapter(movieList)
                val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(applicationContext)
                recyclerView?.layoutManager = layoutManager
                recyclerView?.itemAnimator = DefaultItemAnimator()
                recyclerView?.adapter = adapter
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart(): " + MovieListActivity::class.java.canonicalName)
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume(): " + MovieListActivity::class.java.canonicalName)
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart(): " + MovieListActivity::class.java.canonicalName)
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause(): " + MovieListActivity::class.java.canonicalName)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop(): " + MovieListActivity::class.java.canonicalName)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy(): " + MovieListActivity::class.java.canonicalName)
    }

    private fun arrayMovieList(): ArrayList<Movie> {
        // TODO: Fix
        val movieList = ArrayList<Movie>()
        movieList.add(Movie("Star Wars"))
        movieList.add(Movie("El Padrino"))
        movieList.add(Movie("The Matrix"))
        return movieList
    }

    private fun fetchMovieData(): Movie? {
        // TODO: fetch data from the API
        return null
    }

    companion object {
        private const val TAG = "Activity_2"
    }
}