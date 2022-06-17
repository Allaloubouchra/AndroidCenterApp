package com.example.centerapp.rest


import com.example.centerapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Field
import retrofit2.http.POST

interface UserService {
    @POST("login/")
    fun login(@Field("username") username:String, password:String ): Call<User>





}