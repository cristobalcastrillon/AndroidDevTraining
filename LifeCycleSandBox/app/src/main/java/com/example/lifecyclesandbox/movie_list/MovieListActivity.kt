package com.example.lifecyclesandbox.movie_list

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecyclesandbox.R

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list_activity)
        Log.d(TAG, "onCreate(): " + MovieListActivity::class.java.canonicalName)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.movie_list_fragment_container_view, PopularMoviesFragment::class.java, null)
                    .commit()
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

    companion object {
        private const val TAG = "MovieListActivity"
    }
}