package com.example.datastorage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.datastorage.Model.Movie
import com.example.datastorage.Model.User
import com.example.datastorage.Services.InteractionsDBServices
import com.example.datastorage.Services.MovieDBServices
import com.example.datastorage.Services.UserDBServices
import kotlinx.android.synthetic.main.activity_movie.*

class MovieActivity : AppCompatActivity(){
    lateinit var movieConnection : MovieDBServices
    lateinit var interactionsConnection : InteractionsDBServices
    lateinit var userConnection : UserDBServices
    private var isLiked=false
    private lateinit var user : User
    private lateinit var movie : Movie
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val extras = this.intent.extras
        movieConnection= MovieDBServices(this)
        interactionsConnection= InteractionsDBServices(this)
        userConnection = UserDBServices(this)
        user=userConnection.getUser(extras.getString("USERNAME")) as User
        movie=movieConnection.getMovie(extras.getString("TITLE_MOVIE")) as Movie
        title_movie.text=movie?.title
        duration_movie.text=movie?.duration.toString()
        year_movie.text=movie?.year.toString()
        genre_movie.text=movie?.genre
        var castString = ""
        //for (c in movie!!.cast){
        //    castString+=c+", "
        //}
        castString="Cast"//castString.take(castString.length-2)
        cast_movie.text=castString
        synopsis_movie.text=movie?.synopsis

        if(interactionsConnection.isFavoriteMovie(user!!, movie!!)){
            add_del_favorites_movie.text="Eliminar pelicula de favoritos"
            isLiked=true
        }
    }
    fun addDelLikeClicked(view: View){
        if(isLiked){
            if(interactionsConnection.unlikeMovie(user, movie)){
                Toast.makeText(this, "Pelicula removida de favoritos", Toast.LENGTH_SHORT).show()
                add_del_favorites_movie.text="Agregar a Pelicula a favoritas"
                isLiked=false
            }
        }else {
            if(interactionsConnection.likeMovie(user, movie)){
                Toast.makeText(this, "Pelicula agregada a favoritos", Toast.LENGTH_SHORT).show()
                add_del_favorites_movie.text="Eliminar Pelicula De favoritas"
                isLiked=true
            }
        }
    }
}