package com.example.datastorage.Model

/**
 * Created by ruben4181 on 29/04/2019.
 */

//CREATE TABLE tags(ID_TAG int primarykey, token text, ID_MOVIE)

data class Tag(val ID : Int, val token : String, val ID_MOVIE : Int)