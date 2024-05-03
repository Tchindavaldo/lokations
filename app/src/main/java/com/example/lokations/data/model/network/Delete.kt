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

class Delete {

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.175:80/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val userService = retrofit.create(UserServicePost::class.java)



        fun deleteUser(context: Context) {


         //   val requestBody = jsonString.toRequestBody("application/json".toMediaTypeOrNull())
            val call = userService.deleteUser(1)



            // Envoyer la requête DELETE de manière asynchrone en utilisant une Callback
            call.enqueue(object : Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        // La requête PUT a réussi

                        Toast.makeText(context.applicationContext, "supression reussi", Toast.LENGTH_SHORT).show()
                    } else {
                        // La requête PUT a échoué
                    }
                }




                override fun onFailure(call: Call<Void>, t: Throwable) {
                    // La requête PUT a échoué
                    Toast.makeText(context.applicationContext, "echoué", Toast.LENGTH_SHORT).show()
                }



            })


        }

}