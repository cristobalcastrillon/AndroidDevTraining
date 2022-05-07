package com.example.lifecyclesandbox.movie_list

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lifecyclesandbox.movie_list.favorite.FavoriteMovieListFragment
import com.example.lifecyclesandbox.movie_list.popular.PopularMovieListFragment

class MovieListCollectionAdapter(activity: AppCompatActivity, private val itemsCount: Int) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        lateinit var movieListFragment : Fragment
        when( position ){
            0 -> movieListFragment = PopularMovieListFragment()
            1 -> movieListFragment = FavoriteMovieListFragment()
        }
        return movieListFragment
    }
}
