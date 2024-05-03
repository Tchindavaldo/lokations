package com.example.lokations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.lokations.data.model.network.retrofitInstance
import com.example.lokations.data.model.network.user
import com.squareup.picasso.Picasso
import me.relex.circleindicator.CircleIndicator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


lateinit var  idtx: TextView
lateinit var  nom: TextView
lateinit var loginn : TextView
lateinit var imageview : ImageView

class GetTest2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_test2)


        idtx  = findViewById(R.id.id)
        nom  = findViewById<TextView>(R.id.nom)
        loginn = findViewById<TextView>(R.id.login)
        imageview  = findViewById(R.id.image)

        val userService = retrofitInstance.buildUserService()

        val itemList = ArrayList<ViewItem>()
        itemList.add(ViewItem(R.drawable.login_img1))
        itemList.add(ViewItem(R.drawable.login_img2))
        itemList.add(ViewItem(R.drawable.login_img3))
        itemList.add(ViewItem(R.drawable.login_img4))
        itemList.add(ViewItem(R.drawable.login_img5))
        itemList.add(ViewItem(R.drawable.login_img6))

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.setClipToPadding(false)
        viewPager.clipChildren=false
        viewPager.offscreenPageLimit=4




      //  viewPager.setPadding(200, 0, 200, 0)
        viewPager.setPageMargin(-460)
        viewPager.setPageTransformer(true, object : ViewPager.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val scaleFactor = 0.35f
                val absPosition = Math.abs(position)
                if (absPosition > 1) {
                    page.alpha = 0f
                } else {
                    page.alpha = 1f - absPosition
                    page.scaleX = scaleFactor + (1 - scaleFactor) * (1 - absPosition)
                    page.scaleY = scaleFactor + (1 - scaleFactor) * (1 - absPosition)
                }
            }
        })
        val adapter = ViewPagerAdapter(this, itemList)
        viewPager.adapter = adapter

        val indicator = findViewById<CircleIndicator>(R.id.indicator)
        indicator.setViewPager(viewPager)

      /*  userService.getUser().enqueue(object : Callback<List<user>> {
            override fun onResponse(call: Call<List<user>>, response: Response<List<user>>) {


                if(response.isSuccessful){
                    val items = response.body()
                    items?.let{

                        val ids = items[0].id
                        id.text = ids.toString()
                        nom.text = items[0].nom
                        loginn.text = items[0].login

                        Picasso.get().load("http://192.168.100.175:80/lokation/mp.jpg").centerCrop().into(imageview)
                    }
                }
            }

            override fun onFailure(call: Call<List<user>>, t: Throwable) {

                Toast.makeText(this@GetTest2Activity, "erreur server", Toast.LENGTH_LONG).show()
            }
        })*/
    }
}