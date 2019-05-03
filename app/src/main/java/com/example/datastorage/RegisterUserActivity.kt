package com.example.datastorage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.datastorage.Model.User
import com.example.datastorage.Services.UserDBServices
import kotlinx.android.synthetic.main.activity_register_user.*

class RegisterUserActivity : AppCompatActivity(){
    lateinit var dbConnection : UserDBServices
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_register_user)

        dbConnection = UserDBServices(this)
    }

    fun singUpUser(view : View){
        var user = User(null, username_reg.text.toString(), password_reg.text.toString(), email_reg.toString(),
            first_name_reg.text.toString(), last_name_reg.toString(), age_reg.text.toString().toInt(), "01-05-2019",
            "MT-URL")
        if (dbConnection.registerUser(user)){
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
        }
    }
}