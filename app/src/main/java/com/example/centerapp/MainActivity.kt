package com.example.centerapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.centerapp.models.User
import com.example.centerapp.rest.RetrofitClient
import com.example.centerapp.rest.UserService
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var userService: UserService

    private lateinit var unsername : TextInputEditText
    private lateinit var password : TextInputEditText

    private lateinit var button_to_login: Button
    private lateinit var button_to_create_account: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val username = findViewById<View>(R.id.userName) as EditText
        val password = findViewById<View>(R.id.password) as EditText

        userService = RetrofitClient.client.create(UserService::class.java)


        button_to_login = findViewById(R.id.LoginBtn)
        button_to_login.setOnClickListener {
            fun login(username: String, password: String){
                userService.login(username,password).enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if(response.isSuccessful)
                        {   val user = response.body()!!
                            if (user.user_type == "RECEPTIONIST")
                               startActivity(Intent(applicationContext,ListRecep::class.java))
                            if (user.user_type == "DOCTOR")
                               startActivity(Intent(applicationContext,AddSurvey::class.java))
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })


            }
            }


        button_to_create_account = findViewById(R.id.CreateAccount)
        button_to_create_account.setOnClickListener { startActivity(Intent(this,SignIn::class.java))}

       username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                button_to_login.setEnabled(!s.toString().isEmpty())
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                button_to_login.setEnabled(!s.toString().isEmpty())
            }
        }
        )
    }

}







