package com.example.datastorage.Adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.datastorage.Model.Movie
import com.example.datastorage.R

class MoviesListAdapter(val activity : Activity, val items : List<Movie>?) : BaseAdapter(){
    private var moviesList = ArrayList<Movie>()
    init {
        moviesList = items as ArrayList<Movie>
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view : View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(R.layout.row_movies_list, null)
        val title = view.findViewById<TextView>(R.id.title_movie_row)
        val duration = view.findViewById<TextView>(R.id.duration_movie_row)
        val year = view.findViewById<TextView>(R.id.year_movie_row)
        val genre = view.findViewById<TextView>(R.id.genre_movie_row)
        //val photo = view.findViewById<ImageView>(R.id.image_movie_row)

        title.text=moviesList[position].title
        duration.text=moviesList[position].duration.toString()
        year.text=moviesList[position].year.toString()
        genre.text=moviesList[position].genre

        return view
    }

    override fun getItem(position: Int): Any {
        return moviesList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return moviesList.size
    }

}