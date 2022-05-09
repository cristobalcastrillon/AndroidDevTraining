package com.example.lifecyclesandbox.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDAO {
    @Query("SELECT * FROM moviedata")
    fun getAll(): List<MovieData>

    @Query("SELECT * FROM moviedata WHERE id = :id")
    fun getByID(id : Long): MovieData

    @Query("SELECT * FROM moviedata WHERE id IN (:ids)")
    fun loadAllByIDs(ids : Long): List<MovieData>

    @Query("SELECT * FROM moviedata WHERE favorite = 1")
    fun loadAllFavorites(): List<MovieData>

    @Insert
    fun insertAll(vararg movies: MovieData)

    @Delete
    fun delete(movie: MovieData)

    @Query("DELETE FROM moviedata WHERE id = :id")
    fun deleteByID(id : Long)
}