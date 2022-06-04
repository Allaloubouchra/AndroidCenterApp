package com.example.centerapp

import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class Survey : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_survey)
        supportActionBar?.hide()
        var  RadioGroup1 : RadioGroup
        var  radioButton1 :RadioButton

        var  RadioGroup2 : RadioGroup
        var  radioButton2:RadioButton


        var  RadioGroup3 : RadioGroup
        var  radioButton3 :RadioButton

        RadioGroup1 = findViewById(R.id.radioGroup1)
        RadioGroup2 = findViewById(R.id.radioGroup2)
        RadioGroup3 = findViewById(R.id.radioGroup3)

        val selectedId1: Int =
            RadioGroup1.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId1!= -1) {
            val radioButton1: RadioButton
            radioButton1 = findViewById<View>(selectedId1) as RadioButton
        }

        val selectedId2: Int =
            RadioGroup2.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId2!= -1) {
            val radioButton1: RadioButton
            radioButton2 = findViewById<View>(selectedId2) as RadioButton
        }

        val selectedId3: Int =
            RadioGroup3.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId3!= -1) {
            val radioButton3: RadioButton
            radioButton3 = findViewById<View>(selectedId3) as RadioButton
        }








    }
    }
