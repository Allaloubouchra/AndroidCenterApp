package com.example.centerapp.rest

import com.example.centerapp.models.Appointment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AppointmentService {
    @GET("vaccination-appointment/{id}/")
    fun getAppointmentById(@Path("id") id: Long): Call<Appointment>

}