package com.example.lokations.data.model.network

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface UserServicePost {
    @PUT("/lokation/update.php")
    fun updateUser(@Body userJson: RequestBody): Call<ResponseBody>

    @POST("/lokation/post2.php")

    fun postUser(@Body userJson: RequestBody): Call<ResponseBody>

    @DELETE("/lokation/delete.php/{id}")
    fun deleteUser(@Path("id") userId: Int): Call<Void>

    @Multipart
    @POST("/lokation/img.php")
    fun uploadImage(@Part image: MultipartBody.Part): Call<ResponseBody>

    @Multipart
    @POST("/lokation/txt.php")
    fun uploadText(@Part text: MultipartBody.Part): Call<ResponseBody>

    @Multipart
    @POST("/lokation/zik.php")
    fun uploadZik(@Part zik: MultipartBody.Part): Call<ResponseBody>
}