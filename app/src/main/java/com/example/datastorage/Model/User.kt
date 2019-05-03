package com.example.datastorage.Model

/**
 * Created by ruben4181 on 29/04/2019.
 */
//CREATE TABLE users(ID_USER int primarykey, username text, password text, email text, first_name text, last_name text, age int, sign_in_date text, profile_picture text)
//SELECT ID_USER, username, password, email, first_name, last_name, age, sign_in_date, profile_picture FROM users WHERE 1=1

data class User(val ID : Int?, val username : String?, val password : String?, val email : String?, val firstName :  String?, val lastName : String?, val age : Int?,
                val signInDate : String?, val profilePicture : String?)