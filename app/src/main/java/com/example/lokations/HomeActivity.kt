package com.example.lokations

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Fade


import android.view.Window
import android.view.WindowManager

import android.view.WindowManager.LayoutParams
import com.example.lokations.data.model.network.Delete
import com.example.lokations.data.model.network.Post
import com.example.lokations.data.model.network.UploadImage
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.tabs.TabLayout

class HomeActivity : AppCompatActivity() {

    lateinit var intent2: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     /*   window.setFlags(LayoutParams.FLAG_FULLSCREEN or LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            LayoutParams.FLAG_FULLSCREEN or LayoutParams.FLAG_LAYOUT_NO_LIMITS)*/
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()
        setContentView(R.layout.home)
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)

        var fadeTime :Int =3000
        val tr : Fade
        // Définition de l'animation de fondue
        val enterAnim = R.anim.fade_in_bottom_nav
        val exitAnim = R.anim.fade_out_bottom_nav
      //  tr.duration(500)

     //val post = Post()
      //  post.postUser(this@HomeActivity)

       // val delete = Delete()
        //delete.deleteUser(this@HomeActivity)

        val img= UploadImage()
      // img.uploadImg(this@HomeActivity)
       /* for (i in 0 until 10) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_frame_layout, fragment_param()).commit()
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_frame_layout, fragment_notif()).commit()
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_frame_layout, fragment_boutique()).commit()
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_frame_layout, fragment_search()).commit()*/
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_frame_layout, HomeFragment()).commit()
//setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).
      //  }
        val bottom_navi : BottomNavigationView = findViewById(R.id.bottom_navigation)
        var selectfrg2  : Fragment? =null

        bottom_navi.setOnItemSelectedListener {

            menuItem -> when(menuItem.itemId){

                R.id.page_1 -> { selectfrg2  = HomeFragment()
                    fadeTime =3000
                            true}

                R.id.page_2 -> { selectfrg2  = fragment_search()
                    fadeTime =3000
                    true}

R.id.page_3 -> { selectfrg2  = fragment_boutique()
                   // bottom_navi.backgroundTintList= ColorStateList.valueOf(Color.parseColor("#BEFFFFFF"))
                    //bottom_navi.itemTextColor = ColorStateList.valueOf(Color.parseColor("#25121F"))
                    //bottom_navi.itemBackground = ColorDrawable(Color.parseColor("#25121F"))
    fadeTime =3000
                            true}

                R.id.page_4 -> { selectfrg2  = fragment_notif()
                    fadeTime =3000
                            true}

                R.id.page_5 -> { selectfrg2  = fragment_param()
                    fadeTime =3000
                            true}

            else -> {false}
        }
            if (selectfrg2!=null){

                supportFragmentManager.beginTransaction().replace(R.id.home_frame_layout, selectfrg2!!).commit()
                true
//.setCustomAnimations(enterAnim, exitAnim)
                    //.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)


            }else{false}
        }







        val listdataHome = ArrayList<HomeModel>()




        // This will pass the ArrayList to our Adapter

        //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()

    }


}


