package com.example.centerapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.centerapp.models.User
import com.example.centerapp.rest.RetrofitClient
import com.example.centerapp.rest.UserService
import com.example.centerapp.rest.apiKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val TAG: String? = MainActivity::class.simpleName

    lateinit var userService: UserService

    private lateinit var username: EditText
    private lateinit var password: EditText

    private lateinit var loginButton: Button

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        requestPermissions(arrayOf(Manifest.permission.CAMERA), 102)

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        username = findViewById<View>(R.id.userName) as EditText
        password = findViewById<View>(R.id.password) as EditText
        loginButton = findViewById(R.id.LoginBtn)
        loginButton.setOnClickListener { login(username.text.toString(), password.text.toString()) }

        userService = RetrofitClient.client.create(UserService::class.java)



        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginButton.setEnabled(!s.toString().isEmpty())
            }
        })

        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                loginButton.setEnabled(!s.toString().isEmpty())
            }
        })

        if (checkLoggedIn()) {
            apiKey = sharedPreferences.getString("api_key", null)
            userService.getUser()
                .enqueue(object : Callback<User> {
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        if (response.isSuccessful) {
                            val user = response.body()!!
                            if (user.user_type == "R")
                                startActivity(Intent(applicationContext, ListRecepActivity::class.java))
                            if (user.user_type == "D")
                                startActivity(Intent(applicationContext, AddSurvey::class.java))
//                            finish()

                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT)
                            .show()
                    }
                })

        }
    }

    private fun checkLoggedIn(): Boolean {
        return sharedPreferences.getString("api_key", null) != null
    }

    private fun setLoggedIn(token: String) {
        sharedPreferences.edit().putString("api_key", token).apply()
    }

    private fun login(username: String, password: String) {
        userService.login(mapOf("username" to username, "password" to password))
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val user = response.body()!!
                        setLoggedIn(user.token)
                        apiKey = user.token
                        if (user.user_type == "R")
                            startActivity(Intent(applicationContext, ListRecepActivity::class.java))
                        if (user.user_type == "D")
                            startActivity(Intent(applicationContext, AddSurvey::class.java))
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(applicationContext, "Login Failed", Toast.LENGTH_SHORT).show()
                }
            })
    }

}
