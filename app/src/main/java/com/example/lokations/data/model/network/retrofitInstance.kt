package com.example.lokations.data.model.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object retrofitInstance {

    const val BASE_URL = "http://192.168.100.175:80/"

    private val retrofit by lazy {   Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()}

    fun buildUserService(): userService = retrofit.create(userService::class.java)
}