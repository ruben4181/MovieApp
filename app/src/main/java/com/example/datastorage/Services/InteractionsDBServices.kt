package com.example.datastorage.Services

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.datastorage.Model.Movie
import com.example.datastorage.Model.User

class InteractionsDBServices(context : Context) : SQLiteOpenHelper(context, "InteractionsDBServices", null, 1), IInteractionsServices{
    private lateinit var moviesCon : MovieDBServices

    override fun likeMovie(user: User, movie: Movie): Boolean {
        if(!isFavoriteMovie(user, movie)){
            val interaction = ContentValues()
            interaction.put("TITLE", movie.title)
            interaction.put("USERNAME", user.username)
            this.executeModification(interaction)
            if(isFavoriteMovie(user, movie)){
                return true
            }else{
                return false
            }
        }
        return false
    }

    override fun isFavoriteMovie(user: User, movie: Movie): Boolean {
        val query="SELECT 'y' FROM interactions WHERE TITLE='${movie.title}' AND USERNAME='${user.username}'"
        val result = this.executeQuery(query, this.readableDatabase)
        if(result.moveToFirst()){
            return true
        }
        return false
    }

    override fun unlikeMovie(user: User, movie: Movie): Boolean {
        val query="DELETE FROM interactions WHERE title='${movie.title}' AND USERNAME='${user.ID}'"
        val result=this.executeQuery(query, this.writableDatabase)
        return true
    }

    override fun getAllLikedMovies(user: User) : ArrayList<String> {
        val query="SELECT TITLE FROM interactions WHERE USERNAME='${user.username}'"
        val result = this.executeQuery(query, this.readableDatabase)
        val movies = ArrayList<String>()
        result.moveToFirst()
        while(!result.isAfterLast){
            movies.add(result.getString(0))
            result.moveToNext()
        }
        return movies
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query="CREATE TABLE interactions(ID_INTERACTION int primary key, TITLE text," +
                "USERNAME text)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun executeModification(movie : ContentValues){
        val bd = this.writableDatabase
        bd.insert("interactions", null, movie)
        bd.close()
    }

    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql,null)
        return consulta
    }
}