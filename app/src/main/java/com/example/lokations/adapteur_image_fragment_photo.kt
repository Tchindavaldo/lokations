package com.example.lokations

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter


class adapteur_image_fragment_photo(private val context: Context, private val itemList: ArrayList<ViewItem>) : PagerAdapter() {
    override fun getCount(): Int {
        return itemList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.inflate_view_pager_image_fragment_photo, container, false)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        imageView.setImageResource(itemList[position].image)

        // Charger l'image avec BitmapFactory.decodeResource et la redimensionner en fonction de sa position dans le ViewPager
        val image = BitmapFactory.decodeResource(context.resources, itemList[position].image)
        // var resizedImage:Bitmap;
        // if(position==0){ imageView.setImageBitmap(  resizeImage(image, 400, 200) ) }
        //  if(position==1){ imageView.setImageBitmap(  resizeImage(image, 100, 100) )}
        //  if(position==2){ imageView.setImageBitmap(  resizeImage(image, 100, 100) ) }

        /*  val resizedImage = when (position) {
              0 -> resizeImage(image, 400, 200)
              1 -> resizeImage(image, 50, 100)
              2 -> resizeImage(image, 50, 100)
              else -> image
          }*/
        //  imageView.setImageBitmap(   resizeImage(image, 100, 200) )

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