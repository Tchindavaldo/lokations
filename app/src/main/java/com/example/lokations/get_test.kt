package com.example.lokations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.lokations.data.model.network.retrofitInstance
import com.example.lokations.data.model.network.user
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET




class get_test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_test)
/*
         id  = findViewById(R.id.id)
         nom  = findViewById<TextView>(R.id.nom)
         loginn = findViewById<TextView>(R.id.login)
        imageview  = findViewById(R.id.image)

         val userService = retrofitInstance.buildUserService()

        userService.getUser().enqueue(object : Callback<List<user>>{
            override fun onResponse(call: Call<List<user>>, response: Response<List<user>>) {


                if(response.isSuccessful){
                    val items = response.body()
                    items?.let{

                        val ids = items[0].id
                        id.text = ids.toString()
                        nom.text = items[0].nom
                        loginn.text = items[0].login

                        Picasso.get().load("http://192.168.100.175:80/lokation/mp.jpg").into(imageview)
                    }
                }
            }

            override fun onFailure(call: Call<List<user>>, t: Throwable) {

                Toast.makeText(this@get_test, "erreur server", Toast.LENGTH_LONG).show()
            }
        })
*/
    }
}