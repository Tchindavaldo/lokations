package com.example.lokations


import android.content.Context
import android.os.Build
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class adapteur_recycleView_chambre_info(private val recyclerView: RecyclerView ,private val fmn:FragmentManager, private val data_info_chambre :ArrayList<data_fragment_infos_chambre>,val context: Context) : RecyclerView.Adapter<adapteur_recycleView_chambre_info.ViewHolder>() {





    // create new views
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_chambre_infos, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
val nextPosition = position + 1
        val data = data_info_chambre[position]

        if(position==0){

            holder.item.text = data.item

            if (nextPosition<data_info_chambre.size){
                val nextData=nextPosition
                val nextViewHold = recyclerView.findViewHolderForAdapterPosition(nextPosition) as? ViewHolder

                nextViewHold?.let {
                    viewHolder -> viewHolder.item.text= nextPosition.toString()
                }
            }
        }else
        {
            if (nextPosition<data_info_chambre.size){
                val nextData=nextPosition
                val nextViewHold = recyclerView.findViewHolderForAdapterPosition(nextPosition) as? ViewHolder

                nextViewHold?.let {
                        viewHolder -> viewHolder.item.text= nextPosition.toString()
                }
            }
        }
        //    holder.tabLayout.visibility = View.GONE



        //,Items_data_info_chambre[0],
        //Items_data_info_chambre[1],Items_data_info_chambre[2],Items_data_info_chambre[3]
   //     holder.linkTab(fmn, Items_data_info_chambre)

    //    val containerId = View.generateViewId() // Génère un nouvel identifiant unique
    //    holder.frame_layout.id = containerId // Affecte l'identifiant unique au conteneur

       // fmn.beginTransaction().replace(holder.frame_layout.id, Items_data_info_chambre.infos4).commit()











    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return data_info_chambre.size
    }


    // Holds the views for adding it to image and text
    @RequiresApi(Build.VERSION_CODES.M)

    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {


     //   val tabLayout = itemView.findViewById<TabLayout>(R.id.tabLayout)

        var item: TextView = itemView.findViewById(R.id.item)

      //  fun linkTab(vp:ViewPager2){
           // this.tabLayout.addTab(this.tabLayout.newTab().setText("contrat"))

            /* TabLayoutMediator(this.tabLayout,  this.frame_layout) { tab, position ->
                 if (position == 0) {
                     tab.text="chambre"

                 } else if (position == 1) {
                     tab.text="infos"


                 } else if (position == 2) {
                     tab.text="locataire"
                 } else if (position == 3) {
                     tab.text="contrat"
                 }
             }.attach()*/



/*
        fun setupViewPager(viewPager_infos_chambre:ViewPager2) {

            viewPager_infos_chambre.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    if (state == ViewPager2.SCROLL_STATE_DRAGGING) {
                        // Bloquer le défilement du parent (RecyclerView)
                        viewPager_infos_chambre.parent.requestDisallowInterceptTouchEvent(true)
                    } else if (state == ViewPager2.SCROLL_STATE_IDLE || state == ViewPager2.SCROLL_STATE_SETTLING) {
                        // Autoriser le défilement du parent (RecyclerView)
                        viewPager_infos_chambre.parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
            })
        }*/
/*
        init {
            viewPager_infos_chambre.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        // Bloquer le défilement du parent (RecyclerView)
                       adapteur_scroll_inParent(fragment_boutique()).tru()
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        // Autoriser le défilement du parent (RecyclerView)
                        adapteur_scroll_inParent(fragment_boutique()).fal()
                    }
                }
                false
            }
        }
*/

/*

        fun startAutoScroll(viewPager: ViewPager2, delayStart: Long, delaySlide: Long, transitionDuration: Int) {


            val customTransformer = AdapteurPagerScroller_viewpager2()
customTransformer.setScrollDuration(transitionDuration) // Durée de défilement personnalisée en millisecondes
viewPager.setPageTransformer(customTransformer)

            val handler = Handler()
            var runnable: Runnable? = null

            runnable = object : Runnable {
                override fun run() {
                    val currentItem = viewPager.currentItem
                    val totalItems = viewPager.adapter?.itemCount ?: 0
                    val nextItem = (currentItem + 1) % totalItems

                    viewPager.setCurrentItem(nextItem, true)
                    handler.postDelayed(this, delaySlide)
                }
            }

            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, delayStart)

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrollStateChanged(state: Int) {
                    if (state == ViewPager2.SCROLL_STATE_IDLE) {
                        // Réinitialiser le délai après le défilement manuel de l'utilisateur
                        handler.removeCallbacks(runnable)
                        handler.postDelayed(runnable, delaySlide)
                    }
                }
            })
        }*/


    }


}
