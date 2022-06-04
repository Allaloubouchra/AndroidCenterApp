package com.example.centerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class AddSurvey : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var button_newSurvey: Button
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)
        supportActionBar?.hide()

        button_newSurvey = findViewById(R.id.newSurvey)
        button_newSurvey.setOnClickListener { startActivity(Intent(this,Survey::class.java))}
    }
}