package com.example.datastorage.Services

import com.example.datastorage.Model.Movie


/**
 * Created by ruben4181 on 29/04/2019.
 */
interface IMovieServices{
    fun getAllMovies() : List<Movie>?
    fun getMovie(TITLE_MOVIE : String) : Movie?
    fun filterMovieByYear(year : Int) : List<Movie>?
    fun existsMovie(movie : Movie) : Boolean
    fun saveMovie(movie : Movie) : Boolean
}