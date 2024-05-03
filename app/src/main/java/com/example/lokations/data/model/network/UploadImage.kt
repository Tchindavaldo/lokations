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

class UploadImage {

    val gson = GsonBuilder().setLenient().create()

    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.100.175:80/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val userService = retrofit.create(UserServicePost::class.java)



        fun uploadImg(context: Context) {



            val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.m91)
            val filename = "91.jpg"
            val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename)

// Créer le fichier s'il n'existe pas encore
            if (!file.exists()) {
                file.createNewFile()
            }

// Enregistrer l'objet Bitmap dans le fichier
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()

// Créer une instance de RequestBody à partir du fichier
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

// Créer une instance de MultipartBody.Part à partir du fichier
            val imagePart = MultipartBody.Part.createFormData("image", file.name, requestFile)

// Créer une instance de ImageService à partir de Retrofit
            val imageService = retrofit.create(UserServicePost::class.java)

// Appeler la méthode uploadImage pour envoyer l'image
            val call = imageService.uploadImage(imagePart)




            call.enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if (response.isSuccessful) {
                        // La requête PUT a réussi
                        val responseBody = response.body()?.string()
                        Toast.makeText(context.applicationContext, "ajout image reussi $responseBody", Toast.LENGTH_SHORT).show()
                    } else {
                        // La requête PUT a échoué
                    }
                }




                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // La requête PUT a échoué
                    Toast.makeText(context.applicationContext, "img echoué", Toast.LENGTH_SHORT).show()
                }



            })


        }

}