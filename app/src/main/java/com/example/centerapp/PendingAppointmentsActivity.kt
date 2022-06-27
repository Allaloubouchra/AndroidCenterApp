package com.example.centerapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.centerapp.models.Appointment
import com.example.centerapp.rest.AppointmentService
import com.example.centerapp.rest.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PendingAppointmentsActivity : AppCompatActivity() {
    lateinit var pendingAppointmentsList: RecyclerView

    val appointmentService: AppointmentService =
        RetrofitClient.client.create(AppointmentService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pending_appointments)


        pendingAppointmentsList = findViewById(R.id.pendingAppointmentsList)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        appointmentService.getPendingAppointments().enqueue(object : Callback<List<Appointment>> {
            override fun onResponse(
                call: Call<List<Appointment>>,
                response: Response<List<Appointment>>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val adapter = PendingAppointmentAdapter(
                            response.body()!!,
                            this@PendingAppointmentsActivity::acceptAppointment,
                            this@PendingAppointmentsActivity::declineAppointment
                        )
                        pendingAppointmentsList.adapter = adapter
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

    private fun acceptAppointment(id: Long) {
        appointmentService.updateAppointment(id, mapOf("status" to "CO"))
            .enqueue(object : Callback<Appointment> {
                override fun onResponse(call: Call<Appointment>, response: Response<Appointment>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            initRecyclerView()
                        }
                    }
                }

                override fun onFailure(call: Call<Appointment>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "Echéc reessayez plutard",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }

    private fun declineAppointment(id: Long) {
        appointmentService.updateAppointment(id, mapOf("status" to "CA"))
            .enqueue(object : Callback<Appointment> {
                override fun onResponse(call: Call<Appointment>, response: Response<Appointment>) {
                    if (response.isSuccessful) {
                        if (response.body() != null) {
                            initRecyclerView()
                        }
                    }
                }

                override fun onFailure(call: Call<Appointment>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(
                        applicationContext,
                        "Echéc reessayez plutard",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }


}