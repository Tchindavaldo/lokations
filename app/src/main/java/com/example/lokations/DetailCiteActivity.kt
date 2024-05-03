package com.example.lokations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class DetailCiteActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_cite3)


        /*  val imageBackground: ImageView = findViewById(R.id.imgchambre)

        val lieux: TextView = findViewById(R.id.lieux)

       val getImg = intent.getIntExtra("image", 0)

       val getlieux = intent.getStringExtra("lieux")


       imageBackground.setImageResource(getImg)
       lieux.text = "$getlieux"  */



/*
        val container_detail: ConstraintLayout = findViewById(R.id.container_detail)
        val detail_payement: ConstraintLayout = findViewById(R.id.detail_payement)
        val detail_photo: CardView = findViewById(R.id.detail_photo)
        val container_comment: ConstraintLayout = findViewById(R.id.container_comment)






        val button_detail_infos: CardView = findViewById(R.id.button_detail_infos)
        val payementButtonId: CardView = findViewById(R.id.payementBoutton)
        val button_detail_photo: ConstraintLayout = findViewById(R.id.button_detail_photo)
        val button_detail_comment: ConstraintLayout = findViewById(R.id.button_detail_comment)

*/
/*
        val lieux: TextView = findViewById(R.id.lieux)
        val imageBackground: ImageView = findViewById(R.id.imgchambre)
        val imageBackground2: ImageView = findViewById(R.id.imgBackgrounChambre)

        val getlieux = intent.getStringExtra("lieux")
        val getImg = intent.getIntExtra("image", 0)

             lieux.text = "$getlieux"
             imageBackground.setImageResource(getImg)
             imageBackground2.setImageResource(getImg)
*/
    /*
        button_detail_infos.setOnClickListener {


            detail_payement.visibility = View.GONE
            detail_payement.elevation = 0f

            detail_photo.visibility = View.GONE
            detail_photo.elevation = 0f

            container_comment.visibility = View.GONE
            container_comment.elevation = 0f



            container_detail.visibility = View.VISIBLE
            container_detail.elevation = 5f
        }


        payementButtonId.setOnClickListener {
            container_detail.visibility = View.GONE
            container_detail.elevation = 0f

            detail_photo.visibility = View.GONE
            detail_photo.elevation = 0f

            container_comment.visibility = View.GONE
            container_comment.elevation = 0f



            detail_payement.visibility = View.VISIBLE
            detail_payement.elevation = 5f
        }

        button_detail_photo.setOnClickListener {
            container_detail.visibility = View.GONE
            container_detail.elevation = 0f

            detail_payement.visibility = View.GONE
            detail_payement.elevation = 0f

            container_comment.visibility = View.GONE
            container_comment.elevation = 0f

            detail_photo.visibility = View.VISIBLE
            detail_photo.elevation = 5f
        }

        button_detail_comment.setOnClickListener {
            container_detail.visibility = View.GONE
            container_detail.elevation = 0f

            detail_payement.visibility = View.GONE
            detail_payement.elevation = 0f

            detail_photo.visibility = View.GONE
            detail_photo.elevation = 0f

            container_comment.visibility = View.VISIBLE
            container_comment.elevation = 5f
        }

*/



    }
}