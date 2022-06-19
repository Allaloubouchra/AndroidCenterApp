package com.example.centerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.centerapp.models.Appointment


open class Survey : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var appointment: Appointment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        var button_annuler: Button
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        supportActionBar?.hide()

        var RadioGroup1: RadioGroup
        var radioButton1: RadioButton

        var RadioGroup2: RadioGroup
        var radioButton2: RadioButton


        var RadioGroup3: RadioGroup
        var radioButton3: RadioButton

        button_annuler = findViewById(R.id.annuler)
        button_annuler.setOnClickListener { startActivity(Intent(this, AddSurvey::class.java)) }

        RadioGroup1 = findViewById(R.id.radioGroup1)
        RadioGroup2 = findViewById(R.id.radioGroup2)
        RadioGroup3 = findViewById(R.id.radioGroup3)

        val selectedId1: Int =
            RadioGroup1.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId1 != -1) {
            val radioButton1: RadioButton
            radioButton1 = findViewById<View>(selectedId1) as RadioButton
        }

        val selectedId2: Int =
            RadioGroup2.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId2 != -1) {
            val radioButton1: RadioButton
            radioButton2 = findViewById<View>(selectedId2) as RadioButton
        }

        val selectedId3: Int =
            RadioGroup3.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId3 != -1) {
            val radioButton3: RadioButton
            radioButton3 = findViewById<View>(selectedId3) as RadioButton
        }


        val spinnerTem: Spinner = findViewById(R.id.spinnerTem)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_temp,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTem.adapter = adapter
        }


        val spinnerFreqCar: Spinner = findViewById(R.id.spinnerFreqCar)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_freq_cardi,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFreqCar.adapter = adapter
        }

        val spinnerFreqRes: Spinner = findViewById(R.id.spinnerFreqRes)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_freq_respi,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFreqRes.adapter = adapter
        }

        val spinnertens: Spinner = findViewById(R.id.spinnertens)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_tansion,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnertens.adapter = adapter
        }

        val spinnerSatur: Spinner = findViewById(R.id.spinnerSatur)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_satura,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSatur.adapter = adapter
        }

        if (intent.getSerializableExtra("appointment") != null) {
            appointment = intent.getSerializableExtra("appointment") as Appointment
        } else {
            Toast.makeText(
                applicationContext,
                "Pas de rendez-vous selectionné.",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }


    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}
