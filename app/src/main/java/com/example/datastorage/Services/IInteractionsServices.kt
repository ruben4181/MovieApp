package com.example.datastorage.Services

import com.example.datastorage.Model.Movie
import com.example.datastorage.Model.User

interface IInteractionsServices {
    fun likeMovie(user : User, movie : Movie) : Boolean
    fun isFavoriteMovie(user : User, movie : Movie) : Boolean
    fun unlikeMovie(user : User, movie : Movie) : Boolean
    fun getAllLikedMovies(user : User) : ArrayList<String>
}