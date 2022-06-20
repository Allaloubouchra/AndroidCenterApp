package com.example.centerapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.centerapp.models.Appointment
import com.example.centerapp.rest.AppointmentService
import com.example.centerapp.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


open class Survey : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var fulName: EditText
    lateinit var date: EditText
    lateinit var buttonAnnuler: Button
    lateinit var buttonValidate: Button
    lateinit var buttonCancel: Button
    lateinit var pathoRadioGroup: RadioGroup
    lateinit var covidContact: RadioGroup
    lateinit var covidAffected: RadioGroup
    lateinit var radioButton1: RadioButton
    lateinit var radioButton2: RadioButton
    lateinit var radioButton3: RadioButton
    lateinit var spinnerTem: Spinner
    lateinit var spinnerFreqCar: Spinner
    lateinit var spinnerFreqRes: Spinner
    lateinit var spinnertens: Spinner
    lateinit var spinnerSatur: Spinner


    var temperature: String? = ""
    var frequenceCardiaque: String? = ""
    var frequenceRespi: String? = ""
    var tension: String? = ""
    var satur: String? = ""


    var appointment: Appointment? = null

    val appointmentService: AppointmentService =
        RetrofitClient.client.create(AppointmentService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)
        supportActionBar?.hide()


        buttonAnnuler = findViewById(R.id.annuler)
        buttonAnnuler.setOnClickListener { finish() }

        buttonValidate = findViewById(R.id.validateButton)
        buttonValidate.setOnClickListener { validate() }

        buttonCancel = findViewById(R.id.cancelButton)
        buttonCancel.setOnClickListener { cancel() }

        pathoRadioGroup = findViewById(R.id.pathoRadioGroup)
        covidContact = findViewById(R.id.covidContact)
        covidAffected = findViewById(R.id.covidAffected)


        val selectedId1: Int =
            pathoRadioGroup.getCheckedRadioButtonId() // si aucun button radio n'est coché, cette fonction retourne -1
        if (selectedId1 != -1) {
            val radioButton1: RadioButton
            radioButton1 = findViewById<View>(selectedId1) as RadioButton
        }


        //<editor-fold desc="Description">
        spinnerTem = findViewById(R.id.spinnerTem)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_temp,
            android.R.layout.simple_spinner_item
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTem.adapter = adapter
        }
        spinnerTem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                temperature = if (p2 == 0) "F" else "A"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
        //</editor-fold>

        //<editor-fold desc="freq card">
        spinnerFreqCar = findViewById(R.id.spinnerFreqCar)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_freq_cardi,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFreqCar.adapter = adapter
        }
        spinnerFreqCar.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                frequenceCardiaque = when (p2) {
                    0 -> "T"
                    1 -> "N"
                    2 -> "B"
                    else -> ""
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        //</editor-fold>

        //<editor-fold desc="freq respi">
        spinnerFreqRes = findViewById(R.id.spinnerFreqRes)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_freq_respi,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerFreqRes.adapter = adapter
        }
        spinnerFreqRes.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                frequenceRespi = when (p2) {
                    0 -> "E"
                    1 -> "D"
                    2 -> "B"
                    else -> ""
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        //</editor-fold>

        //<editor-fold desc="tansion">
        spinnertens = findViewById(R.id.spinnertens)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_tansion,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnertens.adapter = adapter
        }
        spinnertens.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tension = when (p2) {
                    0 -> "H"
                    1 -> "N"
                    2 -> "O"
                    else -> ""
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        //</editor-fold>

        //<editor-fold desc="satur">
        spinnerSatur = findViewById(R.id.spinnerSatur)
        ArrayAdapter.createFromResource(
            this,
            R.array.array_satura,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerSatur.adapter = adapter
        }
        spinnerSatur.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                satur = if (p2 == 0) "G" else "B"
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        //</editor-fold>

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

    override fun onResume() {
        fulName = findViewById(R.id.fullName)
        date = findViewById(R.id.dateOfBirth)
        fulName.setText(appointment?.patient?.fullName.toString())
        date.setText(appointment?.appointmentDate.toString())

        super.onResume()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    private fun validateData(): Map<String, Any?> {
        val data = mutableMapOf<String, Any?>()
        data["positive_covid"] =
            covidAffected.checkedRadioButtonId == R.id.covidAffectedYes
        data["contamination"] = covidContact.checkedRadioButtonId == R.id.covidContactYes
        // todo handle the disease cases
        data["temperature"] = temperature
        data["heart_rate"] = frequenceCardiaque
        data["respiratory_rate"] = frequenceRespi
        data["blood_pressure"] = tension
        data["oximetry"] = satur
        data["vaccination_appointment"] = appointment?.id

        return data
    }

    private fun validate() {
        val data = validateData()
        appointmentService.validateAppointment(appointment!!.id, data)
            .enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Vaccination validé avec succés",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "Network error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    private fun cancel() {
        val data = validateData()
        appointmentService.cancelAppointment(appointment!!.id, data)
            .enqueue(object : Callback<Void> {
                override fun onResponse(
                    call: Call<Void>,
                    response: Response<Void>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "Vaccination annulé avec succés",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "Network error",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }
}
