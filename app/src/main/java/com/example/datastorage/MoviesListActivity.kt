package com.example.datastorage

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import com.example.datastorage.Adapters.MoviesListAdapter
import com.example.datastorage.Model.Movie
import com.example.datastorage.Model.User
import com.example.datastorage.Services.InteractionsDBServices
import com.example.datastorage.Services.MovieDBServices

class MoviesListActivity : AppCompatActivity(){
    private lateinit var moviesDBConnection : MovieDBServices
    private lateinit var interactionsConnection : InteractionsDBServices
    private lateinit var moviesListAdapter : MoviesListAdapter
    private lateinit var moviesList : List<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_movies_list)
        interactionsConnection = InteractionsDBServices(this)
        moviesDBConnection = MovieDBServices(this)
        moviesList = moviesDBConnection.getAllMovies()!!
        moviesListAdapter = MoviesListAdapter(this, moviesList)
        val moviesListView = this.findViewById<ListView>(R.id.movies_list_view)
        moviesListView.adapter=moviesListAdapter
        moviesListView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val intent = Intent(this, MovieActivity::class.java)
            val movie = moviesListAdapter.getItem(position) as Movie
            intent.putExtra("TITLE_MOVIE", movie.title)
            intent.putExtra("USERNAME", this.intent.extras.getString("USERNAME"))
            this.startActivity(intent)
        }
    }

    fun newMovie(view : View){
        Toast.makeText(this, "Agregar una pelicula nueva", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, AddMovieActivity::class.java)
        this.startActivity(intent)
    }
    fun viewFavoriteMovies(view : View){
        val tmpUser=User(null, this.intent.extras.getString("USERNAME"), null, null, null,
            null, null, null, null)
        val favoritas=interactionsConnection.getAllLikedMovies(tmpUser)
        val moviesFavoritas=ArrayList<Movie>()
        for(i in moviesFavoritas!!){
            if(favoritas.contains(i.title)){
                moviesFavoritas.add(i)
            }
        }
        moviesListAdapter.notifyDataSetChanged()
    }
}