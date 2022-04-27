package com.example.lifecyclesandbox.movie_list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifecyclesandbox.R;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private ArrayList<Movie> movieList;

    public MoviesAdapter(ArrayList<Movie> movieList){
        this.movieList = movieList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movieTitleText;
        // TODO: Add fields on both ViewHolder of CustomAdapter, and Movie (model).

        public ViewHolder(final View view) {
            super(view);
            movieTitleText = view.findViewById(R.id.movie_title_textView);
        }
    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {
        String movieTitle = movieList.get(position).getMovieTitle();
        holder.movieTitleText.setText(movieTitle);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
