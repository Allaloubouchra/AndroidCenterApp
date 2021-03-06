package com.example.centerapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.centerapp.ListRecepModel
import com.example.centerapp.ListRecepAdapter
import android.widget.ImageButton
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.centerapp.R
import com.example.centerapp.models.Appointment
import com.example.centerapp.rest.AppointmentService
import com.example.centerapp.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class ListRecepActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var ConfirmAppointment: Button

    val adapter: ListRecepAdapter = ListRecepAdapter(listOf())

    lateinit var button_log_out: TextView
    private lateinit var sharedPreferences: SharedPreferences

    lateinit var imageButton1: ImageButton

    val appointmentsList = mutableListOf<Appointment>()

    val appointmentService: AppointmentService =
        RetrofitClient.client.create(AppointmentService::class.java)

    private val getBarCode =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::handleResult
        )
    var barcode: String = ""

    private var isScanning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_recep)
        imageButton1 = findViewById(R.id.imageButton1)
        recyclerView = findViewById(R.id.todayAppointmentsList)
        recyclerView.adapter = adapter

        ConfirmAppointment = findViewById(R.id.ConfirmAppointment)

        ConfirmAppointment.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    PendingAppointmentsActivity::class.java
                )
            )
        }

        imageButton1.setOnClickListener { scanQrCode() }

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        button_log_out = findViewById(R.id.SeDeconnecter)
        button_log_out.setOnClickListener {
            logout()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

    private fun logout() {
        sharedPreferences.edit().clear().apply()
    }

    override fun onResume() {
        super.onResume()
        if (!isScanning) {
            isScanning = false
            initRecyclerView()
        }
    }

    private fun initRecyclerView() {
        appointmentService.getTodayAppointments().enqueue(object : Callback<List<Appointment>> {
            override fun onResponse(
                call: Call<List<Appointment>>,
                response: Response<List<Appointment>>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        appointmentsList.clear()
                        appointmentsList.addAll(response.body()!!)
                        adapter.appointmentsList = appointmentsList
                        adapter.notifyDataSetChanged()
                    }
                }
            }

            override fun onFailure(call: Call<List<Appointment>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(applicationContext, "Erreur de chargement", Toast.LENGTH_SHORT)
                    .show()
            }

        })

    }

    private fun scanQrCode() {
        isScanning = true
        getBarCode.launch(Intent(applicationContext, ScannerActivity::class.java))
    }

    private fun handleResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            if (result.data != null && result.data!!.getStringExtra("scanned_code") != null) {
                barcode = result.data!!.getStringExtra("scanned_code")!!
                if (barcode.toLongOrNull() != null) {
        if (appointmentsList.any { it.id == barcode.toLongOrNull() }) {
            val index =
                appointmentsList.indexOfFirst { it.id == barcode.toLongOrNull() }
            appointmentsList[index] = appointmentsList[index].copy(forToday = true)
            adapter.appointmentsList = appointmentsList
            adapter.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "Ce rendez vous est pour aujourd'hui",
                Toast.LENGTH_SHORT
            ).show()
        } else
            Toast.makeText(
                applicationContext,
                "Ce rendez vous n'est pas pour aujourd'hui",
                Toast.LENGTH_SHORT
            ).show()
                }

            }
        }
    }

}