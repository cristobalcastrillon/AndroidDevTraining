package com.example.lifecyclesandbox.db

import android.content.Context
import androidx.room.Room

class LocalDataSource() {
    val db by lazy { Room.databaseBuilder(applicationContext, MovieLocalDatabase::class.java, "movie-database").build() }
    companion object {
        lateinit var applicationContext : Context
    }
}