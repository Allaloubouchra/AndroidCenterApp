package com.example.centerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class SignIn : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var button_login: Button
        lateinit var button_create_account: Button
        lateinit var button_newSurvey: Button

        lateinit var radioButton1: RadioButton
        lateinit var radioButton2: RadioButton
        lateinit var RadioGroup1: RadioGroup

        lateinit var email : EditText
        lateinit var password : EditText
        lateinit var confim_password : EditText


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()


        radioButton1 = findViewById(R.id.radioButton1)
        radioButton2 = findViewById(R.id.radioButton2)
        RadioGroup1 = findViewById(R.id.radioGroup1)

        button_login = findViewById(R.id.LoginBtn)
        button_login.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }



        button_create_account = findViewById(R.id.CreateAccount)
        button_create_account.setOnClickListener {
            when {
                radioButton1.isChecked -> startActivity(Intent(this, AddSurvey::class.java))
                radioButton2.isChecked -> startActivity(Intent(this, ListRecepActivity::class.java))
            }
        }


         email = findViewById<View>(R.id.email) as EditText
         password = findViewById<View>(R.id.password) as EditText
         confim_password = findViewById<View>(R.id.password) as EditText


        val selectedId1: Int =
            RadioGroup1.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId1 != -1) {
            val radioButton1: RadioButton
            radioButton1 = findViewById<View>(selectedId1) as RadioButton
        }


    }
}