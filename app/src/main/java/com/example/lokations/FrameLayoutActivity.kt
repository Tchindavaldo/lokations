package com.example.lokations

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.AttributeSet
import android.view.KeyEvent.ACTION_DOWN
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_DOWN
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.transition.Fade

class FrameLayoutActivity : AppCompatActivity() {
    @SuppressLint("CommitTransaction")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()

        setContentView(R.layout.frame_layout)



        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)



        var selectfrg  : Fragment? = null
        var typeTransac: String? = null
        var bundle: Bundle;

        val btn_detail : LinearLayout = findViewById(R.id.button_detail_infos)
        val btn_payement : LinearLayout = findViewById(R.id.btn_payement)
        val btn_photo : LinearLayout = findViewById(R.id.btn_photo)
        val btn_comment : LinearLayout = findViewById(R.id.btn_comment)
        val btn_locali : LinearLayout = findViewById(R.id.btn_localisation)

        val btn_detail2 : LinearLayout = findViewById(R.id.button_detail_infos2)
        val btn_payement2 : LinearLayout = findViewById(R.id.btn_payement2)
        val btn_photo2 : LinearLayout = findViewById(R.id.btn_photo2)
        val btn_comment2 : LinearLayout = findViewById(R.id.btn_comment2)
        val btn_locali2 : LinearLayout = findViewById(R.id.btn_localisation2)

        var getcategori = intent.getStringExtra("categori")
        var getlieux = intent.getStringExtra("lieux")
        var getItemCategorie = intent.getStringExtra("ItemCategorie")
        var getprix = intent.getStringExtra("prix")

        var getImg = intent.getIntExtra("image", 0)
        val image = BitmapFactory.decodeResource(resources, getImg)
        supportFragmentManager.beginTransaction().replace(R.id.framelayout, fragment_photo.newInstance(getcategori!!,
            getItemCategorie!!,getlieux!!,getprix!!, image)).commit()


        val container_framel_detail : ConstraintLayout = findViewById(R.id.container_framel_detail)



      //  top_detail.translationX = 5f // Déplace le layout hors de l'écran à gauche
       // bottom_detail.translationX = 4f // Déplace le layout hors de l'écran à gauche
        //container_item_nav.translationX = 100f // Déplace le layout hors de l'écran à gauche


       // textViewcatg.text=getcategori
       // textViewLieux.text=getlieux

       // textViewItemCatg.text=getItemCategorie
       // textViewprix.text=getprix

        val fmanager = supportFragmentManager


        val fragment2  = fragment_test2()
        val fragment_payement  = zina3Fragment()

        var transac: FragmentTransaction? = null

        var fragment_detail_isvisible = false
        var fragment_payement_isvisible = false


             //   Toast.makeText(this@FrameLayoutActivity, "deja clické", Toast.LENGTH_SHORT).show()}


moveRightBarRight()
        moveBottomBarBottom()

        val ontlis= View.OnClickListener { view
            ->
            when(view.id){


                R.id.button_detail_infos  -> {selectfrg=fragment_detail.newInstance(getcategori!!,getItemCategorie!!,getlieux!!,getprix!!,image!!)
                    typeTransac="f2"



                    btn_detail2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    moveRightBarRightWithAnimation()
                    initPositionBottomNav()
                }

                R.id.button_detail_infos2  -> {selectfrg=fragment_detail.newInstance(getcategori!!,getItemCategorie!!,getlieux!!,getprix!!,image!!)
                    typeTransac="f2"



                    btn_detail2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                }

                R.id.btn_payement -> {selectfrg=fragment_payement()
                    typeTransac="f2"

                    btn_payement2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    moveRightBarRightWithAnimation()
                    initPositionBottomNav()}

                R.id.btn_payement2 -> {selectfrg=fragment_payement()
                    typeTransac="f2"

                    btn_payement2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    }

                R.id.btn_photo -> {//selectfrg=fragment_photo.newInstance("chambre 5","250 000/ans",image!!)
                    typeTransac="f1"

                    btn_photo2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    }
                R.id.btn_photo2 -> {selectfrg=fragment_photo.newInstance(getcategori!!,getItemCategorie!!,getlieux!!,getprix!!,image!!)
                    typeTransac="f2"

                    btn_photo2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    initPositionRightNav()
                    moveBottomBarBottomWithAnimation()
                    }

                R.id.btn_comment -> {selectfrg=fragment_comment()

                    typeTransac="f2"

                    btn_comment2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    moveRightBarRightWithAnimation()
                    initPositionBottomNav()}

                R.id.btn_comment2 -> {selectfrg=fragment_comment()

                    typeTransac="f2"

                    btn_comment2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_locali2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    }

                R.id.btn_localisation -> {selectfrg=HomeFragment()

                    typeTransac="f2"

                    btn_locali2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    moveRightBarRightWithAnimation()
                    initPositionBottomNav()}

                R.id.btn_localisation2 -> {selectfrg=HomeFragment()

                    typeTransac="f2"

                    btn_locali2.setBackgroundResource(R.drawable.round_item_selected_nav_detail)
                    btn_detail2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_payement2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_photo2.setBackgroundResource(R.drawable.round_item_nav_detail)
                    btn_comment2.setBackgroundResource(R.drawable.round_item_nav_detail)

                    }

                else -> {null}
            }


            if (typeTransac=="f2"){

               supportFragmentManager.beginTransaction().replace(R.id.framelayout, selectfrg!!).commit()
               // requireActivity().supportFragmentManager.beginTransaction().replace(R.id.framelayout, selectfrg!!).commit()
            }


        }


        btn_detail.setOnClickListener(ontlis)
        btn_payement.setOnClickListener(ontlis)
        btn_photo.setOnClickListener(ontlis)
        btn_comment.setOnClickListener(ontlis)
        btn_locali.setOnClickListener(ontlis)

        btn_detail2.setOnClickListener(ontlis)
        btn_payement2.setOnClickListener(ontlis)
        btn_photo2.setOnClickListener(ontlis)
        btn_comment2.setOnClickListener(ontlis)
        btn_locali2.setOnClickListener(ontlis)


        val otl = View.OnTouchListener { view, event ->

            when(event.action){
                MotionEvent.ACTION_DOWN -> {true}
                MotionEvent.ACTION_UP -> {view.performClick(); true}

                else -> {false}
            }
        }

        btn_detail.setOnTouchListener(otl)
        btn_payement.setOnTouchListener(otl)
        btn_photo.setOnTouchListener(otl)
        btn_comment.setOnTouchListener(otl)
        btn_locali.setOnTouchListener(otl)



    }


    override fun onResume() {
        super.onResume()

        initPositionRightNav()

    }



    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
    }

fun moveRightBarRight(){

    val container_item_nav : LinearLayout = findViewById(R.id.container_item_nav)
    container_item_nav.translationX = 98f // Déplace le layout hors de l'écran à gauche

}


    fun moveBottomBarBottom(){
        val container_item_nav2 : LinearLayout = findViewById(R.id.container_item_nav2)
        container_item_nav2.translationY = 102f // Déplace le layout hors de l'écran à gauche

    }

    fun moveRightBarRightWithAnimation(){
        val container_item_nav   = findViewById<LinearLayout>(R.id.container_item_nav)

        container_item_nav?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 0L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationX = 98f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationX(translationX)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }
    }



    fun moveBottomBarBottomWithAnimation(){
        val container_item_nav   = findViewById<LinearLayout>(R.id.container_item_nav2)


        container_item_nav?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 0L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationY = 102f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationY(translationY)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }
    }
    fun initPositionBottomNav(){
        val container_item_nav   = findViewById<LinearLayout>(R.id.container_item_nav2)


        container_item_nav?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 400L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationY = 0f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationY(translationY)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }
    }
    fun initPositionRightNav(){
        val container_item_nav   = findViewById<LinearLayout>(R.id.container_item_nav)

        container_item_nav?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 400L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationX = -2f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationX(translationX)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }
    }
}