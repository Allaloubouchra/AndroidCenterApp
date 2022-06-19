package com.example.centerapp.rest

import com.example.centerapp.models.Center
import retrofit2.Call
import retrofit2.http.GET

interface CenterService {

    @GET("center/")
    fun getAllCenters(): Call<Center>
}