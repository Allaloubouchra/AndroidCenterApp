package com.example.centerapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class SignIn : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var button_login: Button
        lateinit var button_create_account: Button
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        button_login = findViewById(R.id.LoginBtn)
        button_login.setOnClickListener { startActivity(Intent(this, AddSurvey::class.java)) }

        button_create_account = findViewById(R.id.CreateAccount)
        button_create_account.setOnClickListener { startActivity(Intent(this, SignIn::class.java)) }

        val email = findViewById<View>(R.id.email) as EditText
        val password = findViewById<View>(R.id.password) as EditText
        val confim_password = findViewById<View>(R.id.password) as EditText

        val spinner: Spinner = findViewById(R.id.role)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_role,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = this
        }

    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val spinner: Spinner = findViewById(R.id.role)
        spinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}