package com.example.lokations
import android.content.Context
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

class CustomHomeAdapterItemLigne0(private  val context: Context, private  val itemList : ArrayList<dataClass_img_home_slide>) : RecyclerView.Adapter<CustomHomeAdapterItemLigne0.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_ligne0_home, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

           holder.customAdapter(holder.viewPager)

        val adapterViewPager = Adapteur_image_home_slide(context,itemList)
          val adapterViewPager2 = Adapteur_infini(adapterViewPager)
          holder.viewPager.adapter = adapterViewPager2
             holder.startAutoScroll(holder.viewPager,1500,7000,1500)


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return 1
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


             val viewPager = itemView.findViewById<ViewPager>(R.id.viewPager)

        fun customAdapter(viewPager:ViewPager) {
            viewPager.clipToPadding = false
            viewPager.clipChildren=false
            viewPager.offscreenPageLimit=4




            //  viewPager.setPadding(200, 0, 200, 0)
            viewPager.pageMargin = -70
            viewPager.setPageTransformer(true, object : ViewPager.PageTransformer {
                override fun transformPage(page: View, position: Float) {
                    val scaleFactor = 0.70f
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
        }

        fun startAutoScroll(viewPager: ViewPager, delayStart: Long, delaySlide: Long, transitionDuration: Long) {
            val scroller = AdapteurPagerScroller(viewPager.context)
            scroller.setScrollDuration(transitionDuration.toInt())
            scroller.initViewPagerScroll(viewPager)

            val handler = Handler()
            lateinit var runnable: Runnable

            runnable = object : Runnable {
                override fun run() {
                    val currentItem = viewPager.currentItem
                    val totalItems = viewPager.adapter?.count ?: 0
                    val nextItem = (currentItem + 1) % totalItems

                    viewPager.setCurrentItem(nextItem, true)
                    handler.postDelayed(this, delaySlide)
                }
            }

            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, delayStart)
        }

    }
}


