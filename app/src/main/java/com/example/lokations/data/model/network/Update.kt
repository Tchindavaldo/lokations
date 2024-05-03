package com.example.lokations.data.model.network

import android.content.Context
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Update {

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.175:80/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val userService = retrofit.create(UserServicePost::class.java)



        fun updateUser(context: Context) {

            val jsonObject = JsonObject()
            jsonObject.addProperty("name", "Jonny")
            jsonObject.addProperty("age", 23)

            val jsonString = gson.toJson(jsonObject)

            val requestBody = jsonString.toRequestBody("application/json".toMediaTypeOrNull())




            userService.updateUser(requestBody).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // La requête PUT a réussi
                        val responseBody = response.body()?.string()
                        Toast.makeText(context.applicationContext, "reussi $responseBody", Toast.LENGTH_SHORT).show()
                    } else {
                        // La requête PUT a échoué
                    }
                }




                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // La requête PUT a échoué
                    Toast.makeText(context.applicationContext, "echoué", Toast.LENGTH_SHORT).show()
                }



            })


        }

}