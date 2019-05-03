package com.example.datastorage.Services

import com.example.datastorage.Model.User

/**
 * Created by ruben4181 on 29/04/2019.
 */

interface IUserServices{
    fun verifyUser(user : User) : Boolean
    fun existsUser(user : User) : Boolean
    fun registerUser(user : User) : Boolean
    fun getUser(username : String) : User?
}