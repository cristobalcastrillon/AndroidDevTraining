package com.example.lifecyclesandbox.movie_detail

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecyclesandbox.R
import com.example.lifecyclesandbox.login.LoginActivity
import com.example.lifecyclesandbox.movie_list.PopularMoviesFragment

class MovieDetailActivity : AppCompatActivity(R.layout.movie_detail_activity) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val movieID = intent.extras?.getString(Intent.EXTRA_TEXT)

        Log.d(MovieDetailActivity.TAG, "onCreate(): " + LoginActivity::class.java.canonicalName)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.movie_detail_fragment_container_view, MovieDetailFragment::class.java, Bundle().also {
                    it.putString("id", movieID)
                })
                .commit()
        }
    }

    companion object {
        private const val TAG = "MovieDetailActivity"
    }
}