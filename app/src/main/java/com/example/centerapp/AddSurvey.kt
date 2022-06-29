package com.example.centerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.centerapp.models.Appointment
import com.example.centerapp.rest.AppointmentService
import com.example.centerapp.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddSurvey : AppCompatActivity() {
    lateinit var button_newSurvey: Button
    lateinit var button_log_out: Button

    private lateinit var sharedPreferences: SharedPreferences

    val TAG: String? = AddSurvey::class.simpleName
    private val getBarCode =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::handleResult
        )


    val service: AppointmentService = RetrofitClient.client.create(AppointmentService::class.java)
    var barcode: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey)
        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        button_log_out = findViewById(R.id.logOut)
        button_log_out.setOnClickListener {
            logout()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        button_newSurvey = findViewById(R.id.newSurvey)
        button_newSurvey.setOnClickListener { service.getAppointmentById(23)
            .enqueue(object : Callback<Appointment> {
                override fun onResponse(
                    call: Call<Appointment>,
                    response: Response<Appointment>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        handleResponse(response.body()!!)
                    }
                }

                override fun onFailure(call: Call<Appointment>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "Rendez-vous introuvable",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }) }
    }

    private fun scanQrCode() {
        getBarCode.launch(Intent(applicationContext, ScannerActivity::class.java))
    }

    private fun handleResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            if (result.data != null && result.data!!.getStringExtra("scanned_code") != null) {
                barcode = result.data!!.getStringExtra("scanned_code")!!
                if (barcode.toLongOrNull() != null) {
                    service.getAppointmentById(barcode.toLong())
                        .enqueue(object : Callback<Appointment> {
                            override fun onResponse(
                                call: Call<Appointment>,
                                response: Response<Appointment>
                            ) {
                                if (response.isSuccessful && response.body() != null) {
                                    handleResponse(response.body()!!)
                                }
                            }

                            override fun onFailure(call: Call<Appointment>, t: Throwable) {
                                t.printStackTrace()
                                Toast.makeText(
                                    applicationContext,
                                    "Rendez-vous introuvable",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        })

                }

            }
        }
    }

    private fun handleResponse(appointment: Appointment) {
        val intent = Intent(
            applicationContext,
            Survey::class.java
        ).apply { putExtra("appointment", appointment) }
        startActivity(intent)
    }

    private fun logout() {
        sharedPreferences.edit().clear().apply()
    }
}