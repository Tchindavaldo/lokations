package com.example.lokations
import android.content.Context
import android.graphics.Rect
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.viewpager.widget.ViewPager

class CustomHomeAdapterItemLigne2(

    private val data0: ArrayList<dataClass_img_home_slide>,
    private val mList: List<ItemsLigne2Model>,
    val context: Context
) : RecyclerView.Adapter<CustomHomeAdapterItemLigne2.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemligne2, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
val t :Long =500
      /*  holder.customAdapter(holder.viewPager)
        val adapterViewPager = Adapteur_image_home_slide(context, data0)
        val adapterViewPager2 = Adapteur_infini(adapterViewPager)
        holder.viewPager.adapter = adapterViewPager2
        holder.startAutoScroll(holder.viewPager, 1500, 7000, 1500)*/
        // sets the image to the imageview from our itemHolder class

/*holder.ligne2.alpha=0f
//holder.ligne2.animate().alpha(1f).setDuration(800).setStartDelay(300).start()

holder.ligne3.alpha=0f
holder.ligne4.alpha=0f
holder.ligne5.alpha=0f*/
        if(ItemsViewModel.image!=null) {
            holder.imageItem.setImageResource(ItemsViewModel.image)
            holder.card1L1.visibility=View.VISIBLE
            //    holder.imageItem.animate().alpha(1f).setDuration(t).start()

            //    holder.NomCategori.alpha = 0f
            holder.NomCategori.text = ItemsViewModel.categorie;
            //   holder.NomCategori.animate().alpha(1f).setDuration(t).start()

            //   holder.NomItem.alpha = 0f
            holder.NomItem.text = ItemsViewModel.itemCategorie;
            //  holder.NomItem.animate().alpha(1f).setDuration(t).start()

            //   holder.statut.alpha = 0f
            holder.statut.text = ItemsViewModel.text;
            //   holder.statut.animate().alpha(1f).setDuration(t).start()

            //   holder.prix.alpha = 0f
            holder.prix.text = ItemsViewModel.prix;
            // holder.prix.animate().alpha(1f).setDuration(t).start()

            //   holder.ville.alpha = 0f
            holder.ville.text = ItemsViewModel.lieux;
            //  holder.ville.animate().alpha(1f).setDuration(t).start()


            holder.customAdapter(holder.viewPager)
            val adapterViewPager = Adapteur_image_home_slide(context, data0)
            val adapterViewPager2 = Adapteur_infini(adapterViewPager)
            holder.viewPager.adapter = adapterViewPager2

        }

     /*   rvparent.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
                val firstPosiRecup = firstPosi.findFirstVisibleItemPosition()
            if(firstPosiRecup==1){

                holder.prix.animate().alpha(1f).setDuration(t).setStartDelay(2000).start()

            }
            if(position==1){

                if(  holder.itemView.isShown){
                    Toast.makeText(context,"holder 2 est visible",Toast.LENGTH_SHORT).show()
                }
            }

        }})
        */
    /*    if(position==0) {
            holder.containerViewPager.visibility = View.VISIBLE
        }*/





    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val containerViewPager = itemView.findViewById<LinearLayout>(R.id.containerViewPager)

 val viewPager = itemView.findViewById<ViewPager>(R.id.viewPager)
 val container = itemView.findViewById<LinearLayout>(R.id.container)

 val card1L1 = itemView.findViewById<CardView>(R.id.card1L1)

        val ligne2 = itemView.findViewById<LinearLayout>(R.id.ligne2)
 val ligne3 = itemView.findViewById<LinearLayout>(R.id.ligne3)
 val ligne4 = itemView.findViewById<LinearLayout>(R.id.ligne4)
 val ligne5 = itemView.findViewById<LinearLayout>(R.id.ligne5)

        val imageItem: ImageView = itemView.findViewById(R.id.imageItem)
        val NomCategori: TextView = itemView.findViewById(R.id.NomCategori)
        val NomItem: TextView = itemView.findViewById(R.id.NomItem)

        val statut: TextView = itemView.findViewById(R.id.statut)
        val prix: TextView = itemView.findViewById(R.id.prix)
        val ville: TextView = itemView.findViewById(R.id.ville)



        fun customAdapter(viewPager: ViewPager) {
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


