package com.example.lifecyclesandbox.movie_list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2
import com.example.lifecyclesandbox.R
import com.example.lifecyclesandbox.db.MovieLocalDatabase
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MovieListActivity : AppCompatActivity() {

    private lateinit var movieListCollectionAdapter: MovieListCollectionAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.movie_list_activity)

        // Tab titles
        val tabTitles = arrayOf("popular", "favorite")

        // ViewPager
        movieListCollectionAdapter = MovieListCollectionAdapter(this, tabTitles.size)
        viewPager = this.findViewById(R.id.pager)
        viewPager.adapter = movieListCollectionAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
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

    companion object {
        private const val TAG = "MovieListActivity"
    }
}