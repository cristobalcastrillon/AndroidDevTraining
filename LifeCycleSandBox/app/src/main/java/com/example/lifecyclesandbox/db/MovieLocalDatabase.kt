package com.example.lifecyclesandbox.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieData::class], version = 1)
abstract class MovieLocalDatabase : RoomDatabase() {
    abstract fun movieDAO() : MovieDAO
}