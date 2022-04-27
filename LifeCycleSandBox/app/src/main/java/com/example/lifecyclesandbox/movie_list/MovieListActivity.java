package com.example.lifecyclesandbox.movie_list;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifecyclesandbox.R;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {
    private static final String TAG = "Activity_2";
    private ArrayList<Movie> movieList = arrayMovieList();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        recyclerView = findViewById(R.id.movieListRecyclerView);
        Log.d(TAG, "onCreate(): " + MovieListActivity.class.getCanonicalName());

        setAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart(): " + MovieListActivity.class.getCanonicalName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume(): " + MovieListActivity.class.getCanonicalName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart(): " + MovieListActivity.class.getCanonicalName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause(): " + MovieListActivity.class.getCanonicalName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop(): " + MovieListActivity.class.getCanonicalName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy(): " + MovieListActivity.class.getCanonicalName());
    }

    private ArrayList<Movie> arrayMovieList() {
        // TODO: Fix
        ArrayList<Movie> movieList = new ArrayList<>();

        movieList.add(new Movie("Star Wars"));
        movieList.add(new Movie("El Padrino"));
        movieList.add(new Movie("The Matrix"));

        return movieList;
    }

    private Movie fetchMovieData() {
        // TODO: fetch data from the API
        return null;
    }

    private void setAdapter() {
        MoviesAdapter adapter = new MoviesAdapter(movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
