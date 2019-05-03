package com.example.datastorage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.datastorage.Model.User
import com.example.datastorage.Services.InteractionsDBServices
import com.example.datastorage.Services.MovieDBServices
import com.example.datastorage.Services.UserDBServices
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dbConnection : UserDBServices
    lateinit var movieConnection : MovieDBServices
    lateinit var interactionsConnection : InteractionsDBServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbConnection = UserDBServices(this)
        movieConnection= MovieDBServices(this)
        interactionsConnection= InteractionsDBServices(this)
    }

    fun login(view : View){
        val username = user_edittext.text.toString()
        val password = password_edittext.text.toString()
        val user = User(0, username, password, "", "", "", 0, "", "")
        if(dbConnection.verifyUser(user)){
            val intent = Intent(this, MoviesListActivity::class.java)
            intent.putExtra("USERNAME", username)
            this.startActivity(intent)
        }else{
            Toast.makeText(this, "El usuario/password no son correctos", Toast.LENGTH_SHORT).show()
        }
    }

    fun register(view : View){
        val i = Intent(this, RegisterUserActivity::class.java)
        this.startActivity(i)
    }
}