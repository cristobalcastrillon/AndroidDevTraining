package com.example.lifecyclesandbox.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.lifecyclesandbox.movie_list.shared.MovieListViewModel

object MovieDBContract {
    object MovieEntry : BaseColumns {
        const val MOVIE_ID = "id"
        const val TABLE_NAME = "movies"
        const val MOVIE_TITLE = "movie_title"
        const val RELEASE_DATE = "release_date"
        const val OVERVIEW = "overview"
        const val POPULARITY = "popularity"
        const val VOTE_AVERAGE = "vote_average"
        const val POSTER_PATH = "poster_path"
        const val FAVORITE = "favorite"
    }
}

private const val SQL_CREATE_ENTRIES = "CREATE TABLE ${MovieDBContract.MovieEntry.TABLE_NAME} ("+
        "${MovieDBContract.MovieEntry.MOVIE_ID} INTEGER PRIMARY KEY, " +
        "${MovieDBContract.MovieEntry.MOVIE_TITLE} TEXT, " +
        "${MovieDBContract.MovieEntry.RELEASE_DATE} TEXT, " +
        "${MovieDBContract.MovieEntry.OVERVIEW} TEXT, " +
        "${MovieDBContract.MovieEntry.POPULARITY} REAL, " +
        "${MovieDBContract.MovieEntry.VOTE_AVERAGE} REAL, " +
        "${MovieDBContract.MovieEntry.POSTER_PATH} TEXT, " +
        "${MovieDBContract.MovieEntry.POSTER_PATH} INTEGER) "

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MovieDBContract.MovieEntry.TABLE_NAME}"

class MovieLocalDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
    }

    fun addMovie(movieUI : MovieListViewModel.MovieUI) : Unit {
    //fun addMovie(id: Long, title: String, releaseDate: String, overview: String, popularity: Double, voteAverage: Double, posterPath: String, favorite: Boolean) : Unit {
        var db : SQLiteDatabase = this.writableDatabase
        var contentValues = ContentValues()

        contentValues.put(MovieDBContract.MovieEntry.MOVIE_ID, movieUI.id)
        contentValues.put(MovieDBContract.MovieEntry.MOVIE_TITLE, movieUI.title)
        contentValues.put(MovieDBContract.MovieEntry.RELEASE_DATE, movieUI.releaseDate)
        contentValues.put(MovieDBContract.MovieEntry.OVERVIEW, movieUI.overview)
        contentValues.put(MovieDBContract.MovieEntry.POPULARITY, movieUI.popularity)
        contentValues.put(MovieDBContract.MovieEntry.VOTE_AVERAGE, movieUI.voteAverage)
        contentValues.put(MovieDBContract.MovieEntry.POSTER_PATH, movieUI.posterPath)
        contentValues.put(MovieDBContract.MovieEntry.FAVORITE, movieUI.favorite)

        db.insert(MovieDBContract.MovieEntry.TABLE_NAME, null, contentValues)

        db.close()
    }

    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "MovieLocalDatabase"
    }
}