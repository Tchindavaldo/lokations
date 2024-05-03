package com.example.lokations.data.model.network

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.widget.Toast
import com.example.lokations.R
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileOutputStream

class Uploadtexte {

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.175:80/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val userService: UserServicePost = retrofit.create(UserServicePost::class.java)



        fun uploadText(context: Context, text: String?) {

            // Créer une instance de RequestBody à partir du contenu du fichier texte
            val requestBody = text?.toRequestBody("text/plain".toMediaTypeOrNull())!!

            // Créer une instance de MultipartBody.Part à partir du fichier texte
            val textPart = MultipartBody.Part.createFormData("text", null, requestBody)



            // Appeler la méthode uploadText pour envoyer le fichier texte
            val call = userService.uploadText(textPart)

            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // La requête POST a réussi
                        val responseBody = response.body()?.string()
                        Toast.makeText(context.applicationContext, "Ajout texte réussi : $responseBody", Toast.LENGTH_SHORT).show()
                    } else {
                        // La requête POST a échoué
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // La requête POST a échoué
                    Toast.makeText(context.applicationContext, "Ajout texte échoué", Toast.LENGTH_SHORT).show()
                }
            })
        }
}





