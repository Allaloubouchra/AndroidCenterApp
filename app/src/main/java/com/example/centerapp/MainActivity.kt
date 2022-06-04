package com.example.centerapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var button_to_login: Button
    private lateinit var button_to_create_account: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        button_to_login = findViewById(R.id.LoginBtn)
        button_to_login.setOnClickListener { startActivity(Intent(this,AddSurvey::class.java)) }

        button_to_create_account = findViewById(R.id.CreateAccount)
        button_to_create_account.setOnClickListener { startActivity(Intent(this,SignIn::class.java))}

        val email = findViewById<View>(R.id.email) as EditText
        val password = findViewById<View>(R.id.password) as EditText

       email.addTextChangedListener(object : TextWatcher {
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
        ) }

}