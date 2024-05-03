package com.example.lokations



import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class Adapteur_image_home_slide(private val context: Context, private val itemList: ArrayList<dataClass_img_home_slide>) : PagerAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.view_pager_img_slide, container, false)
        val imageView = view.findViewById<ImageView>(R.id.img1Homee)
        val imageView2 = view.findViewById<ImageView>(R.id.img2item2Ligne2)
        val titre = view.findViewById<TextView>(R.id.statut)
        val description = view.findViewById<TextView>(R.id.prix)


        imageView.setImageResource(itemList[position].img)
        imageView2.setImageResource(itemList[position].img)
        titre.text=itemList[position].titre
        description.text=itemList[position].description



        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    private fun resizeImage(image: Bitmap, width: Int, height: Int): Bitmap {
        return Bitmap.createScaledBitmap(image, width, height, true)
    }
}