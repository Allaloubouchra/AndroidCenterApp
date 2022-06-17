package com.example.centerapp.rest

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .setPrettyPrinting()
        .setLenient()
        .create()

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val httpClient = OkHttpClient()
        .newBuilder()
        .addInterceptor(loggingInterceptor)
        .build()

    val client = Retrofit.Builder()
        .baseUrl("http://192.168.1.35/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient)
        .build()


}