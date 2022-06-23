package com.example.centerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.centerapp.ListRecepModel
import com.example.centerapp.ListRecepAdapter
import android.widget.ImageButton
import android.os.Bundle
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

    lateinit var adapter: ListRecepAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_recep)
        imageButton1 = findViewById(R.id.imageButton1)
        recyclerView = findViewById(R.id.todayAppointmentsList)

        imageButton1.setOnClickListener { scanQrCode() }

    }

    override fun onResume() {
        super.onResume()
        initRecyclerView()
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
                        adapter = ListRecepAdapter(response.body()!!)
                        recyclerView.adapter = adapter
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
        getBarCode.launch(Intent(applicationContext, ScannerActivity::class.java))
    }

    private fun handleResult(result: ActivityResult) {
        if (result.resultCode == RESULT_OK) {
            if (result.data != null && result.data!!.getStringExtra("scanned_code") != null) {
                barcode = result.data!!.getStringExtra("scanned_code")!!
                if (barcode.toLongOrNull() != null) {
                    if (appointmentsList.any { it.id == barcode.toLongOrNull() })
                        Toast.makeText(
                            applicationContext,
                            "Ce rendez vous est pour aujourd'hui",
                            Toast.LENGTH_SHORT
                        ).show()
                    else
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