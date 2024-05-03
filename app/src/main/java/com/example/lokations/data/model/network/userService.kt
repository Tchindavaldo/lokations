package com.example.lokations.data.model.network

import retrofit2.Call
import retrofit2.http.GET

interface userService {

    @GET("/lokation/user.php")
    fun getUser(): Call<List<user>>
}