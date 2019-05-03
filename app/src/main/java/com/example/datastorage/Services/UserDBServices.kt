package com.example.datastorage.Services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.datastorage.Model.User

/**
 * Created by ruben4181 on 29/04/2019.
 */
class UserDBServices(context : Context) : SQLiteOpenHelper(context, "UsersDBServices", null, 1), IUserServices{

    override fun onCreate(db : SQLiteDatabase){
        val query = "CREATE TABLE users(ID_USER int primary key, username text, password text, email text, first_name text, last_name text, age int, sign_in_date text, profile_picture text)"
        db?.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registerUser(user: User): Boolean {
        var localUser = ContentValues()
        localUser.put("username", user.username)
        localUser.put("password", user.password)
        localUser.put("email", user.email)
        localUser.put("first_name", user.firstName)
        localUser.put("last_name", user.lastName)
        localUser.put("age", user.age)
        localUser.put("sign_in_date", user.signInDate)
        localUser.put("profile_picture", user.profilePicture)
        this.executeModification(localUser)
        return existsUser(user)
    }

    override fun verifyUser(user: User): Boolean {
        val query = "SELECT username FROM users WHERE username='${user.username}' AND password='${user.password}'"
        val result = this.executeQuery(query, this.readableDatabase)
        var returnValue = false
        if(result.moveToFirst()){
            returnValue=true
        }
        return returnValue
    }
    override fun existsUser(user: User): Boolean {
        val query = "SELECT username FROM users WHERE username='${user.username}' OR email='${user.email}'"
        val result : Cursor = this.executeQuery(query, this.readableDatabase)
        var returnValue = false
        if(result.moveToFirst()){
            returnValue=true
        }
        return returnValue
    }
    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql,null)
        return consulta
    }

    private fun executeModification(user : ContentValues){
        val bd = this.writableDatabase
        bd.insert("users", null, user)
        bd.close()
    }

    override fun getUser(username: String): User? {
        val query = "SELECT ID_USER, username, password, email, first_name, last_name, age, sign_in_date, profile_picture FROM users WHERE username='${username}'"
        val result = this.executeQuery(query, this.readableDatabase)
        if(result.moveToFirst()){
            var user = User(result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getInt(6),
                result.getString(7),
                result.getString(8))
            return user
        }
        return null
    }
}