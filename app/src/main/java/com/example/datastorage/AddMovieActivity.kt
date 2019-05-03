package com.example.datastorage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.datastorage.Model.Movie
import com.example.datastorage.Services.MovieDBServices
import kotlinx.android.synthetic.main.activity_add_movie.*

class AddMovieActivity : AppCompatActivity(){
    private lateinit var moviesDBConnection : MovieDBServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_add_movie)
        moviesDBConnection = MovieDBServices(this)
    }

    fun addMovie(view : View){
        val movie = Movie(null, title_add_movie.text.toString(), director_add_movie.text.toString(),
            year_add_movie.text.toString().toInt(), genre_add_movie.text.toString(), "Junior-Tu Papa-Rrrrda".split("-"),
            "MT-URL", synopsis_add_movie.text.toString(), duration_add_movie.text.toString().toInt())
        if(moviesDBConnection.saveMovie(movie)){
            Toast.makeText(this, "Pelicula guardada correctamente", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Error al guardar la pelicula", Toast.LENGTH_SHORT).show()
        }
    }
}