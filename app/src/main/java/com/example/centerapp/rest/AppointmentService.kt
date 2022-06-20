package com.example.centerapp.rest

import com.example.centerapp.models.Appointment
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AppointmentService {
    @GET("vaccination-appointment/{id}/")
    fun getAppointmentById(@Path("id") id: Long): Call<Appointment>

    @PATCH("vaccination-appointment/{id}/")
    @JvmSuppressWildcards
    fun updateAppointment(@Path("id") id: Long, @Body() data: Map<String, Any>): Call<Appointment>

    @POST("vaccination-appointment/{id}/validate/")
    @JvmSuppressWildcards
    fun validateAppointment(
        @Path("id") id: Long,
        @Body() data: Map<String, Any?>
    ): Call<Void>

    @POST("vaccination-appointment/{id}/cancel/")
    @JvmSuppressWildcards
    fun cancelAppointment(
        @Path("id") id: Long,
        @Body() data: Map<String, Any?>
    ): Call<Void>

}