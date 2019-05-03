package com.example.datastorage.Services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.datastorage.Model.Movie

/**
 * Created by ruben4181 on 29/04/2019.
 */
class MovieDBServices(context : Context) : SQLiteOpenHelper(context, "MoviesDBServices", null, 1), IMovieServices{

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE movies(ID_MOVIE int primary key, title text, director text, year text, genre text, cast_movie text, photo text, synopsis text, duration int)"
        db?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAllMovies(): List<Movie>? {
        val query="SELECT ID_MOVIE, title, director, year, genre, cast_movie, photo, synopsis, duration FROM movies"
        val result = this.executeQuery(query, this.readableDatabase)
        val movies = ArrayList<Movie>()
        result.moveToFirst()
        while(!result.isAfterLast){
            val movie = Movie(result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getInt(3),
                result.getString(4),
                result.getString(5).split("-"),
                result.getString(6),
                result.getString(7),
                result.getInt(8))
            movies.add(movie)
            result.moveToNext()
        }
        return movies
    }

    override fun getMovie(TITLE_MOVIE: String): Movie? {
        val query="SELECT ID_MOVIE, title, director, year, genre, cast_movie, photo, synopsis, duration FROM movies WHERE title='${TITLE_MOVIE}'"
        val result = this.executeQuery(query, this.readableDatabase)
        if (result.moveToFirst()){
            return Movie(result.getInt(0), result.getString(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5).split("-"),
                result.getString(6), result.getString(7), result.getInt(8))
        }
        return null
    }

    override fun saveMovie(movie: Movie): Boolean {
        val movieValues = ContentValues()
        movieValues.put("ID_MOVIE", movie.ID)
        movieValues.put("title", movie.title)
        movieValues.put("director", movie.director)
        movieValues.put("year", movie.year)
        movieValues.put("genre", movie.genre)
        movieValues.put("photo", movie.photo)
        movieValues.put("synopsis", movie.synopsis)
        movieValues.put("duration", movie.duration)

        var cast_movie : String =""

        for(i in movie?.cast){
            cast_movie+=i+"-"
        }
        movieValues.put("cast_movie", cast_movie.take(cast_movie.length-1))
        this.executeModification(movieValues)
        return existsMovie(movie)
    }

    override fun filterMovieByYear(year: Int): ArrayList<Movie>? {
        val query="SELECT ID_MOVIE, title, director, year, genre, cast_movie, photo, synopsis, duration FROM movies WHERE year=${year.toString()}"
        val result = this.executeQuery(query, this.readableDatabase)
        val movies = ArrayList<Movie>()
        result.moveToFirst()
        while(!result.isAfterLast){
            val movie = Movie(result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getInt(3),
                result.getString(4),
                result.getString(5).split("-"),
                result.getString(6),
                result.getString(7),
                result.getInt(8))
            movies.add(movie)
        }
        return movies
    }

    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql,null)
        return consulta
    }

    override fun existsMovie(movie: Movie): Boolean {
        val query="SELECT title FROM movies WHERE title='${movie.title}'"
        val result=this.executeQuery(query, this.readableDatabase)
        var returnValue=false

        if(result.moveToFirst()){
            returnValue=true
        }
        return returnValue
    }


    private fun executeModification(movie : ContentValues){
        val bd = this.writableDatabase
        bd.insert("movies", null, movie)
        bd.close()
    }
}