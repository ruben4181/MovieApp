package com.example.datastorage.Model


/**
 * Created by ruben4181 on 29/04/2019.
 */

//CREATE TABLE movies(ID_MOVIE int primarykey, title text, director text, year text, genre text, cast text, photo text, sinopsis text)
//SELECT ID_MOVIE, title, director, year, genre, cast, photo, synopsis FROM moviews WHERE 1=1

data class Movie(val ID : Int?, val title : String?, val director : String?, val year : Int?,
                 val genre : String?, val cast : List<String>, val photo : String?, val synopsis : String?,
                 val duration : Int?)