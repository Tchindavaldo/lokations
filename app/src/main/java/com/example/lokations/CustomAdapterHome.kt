package com.example.lokations

import com.example.lokations.R
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.animation.AnimationUtils


class CustomAdapterHome(
    private val recyclerView:RecyclerView,
    private val mList0: ArrayList<itemSHomeModel>,
    private val mList: ArrayList<ItemsLigne1>, private val mList2: ArrayList<ItemsLigne2>,
    private val mList3: ArrayList<ItemsLigne3>, private val mList4: ArrayList<ItemsLigne4>,
    private val mList5: ArrayList<ItemsLigne5>,
    private val data_img_home_slide: ArrayList<ArrayList<dataClass_img_home_slide>>,
    val context: Context, val detail_cite: String, val intent: Intent,
) : RecyclerView.Adapter<CustomAdapterHome.ViewHolder>() {





    // create new views
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.homeinflator, parent, false)

        return ViewHolder(view)
    }
    // binds the list items to a view
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nextPosition = position + 1
     //   holder.customAdapter(holder.viewPager)
     //   holder.customAdapter(holder.viewPager2)

        val ItemsHome = mList0[position]
        val ItemsLigne1 = mList[position]
        val ItemsLigne2 = mList2[position]



        val ItemsLigne3 = mList3[position]
        val ItemsLigne4 = mList4[position]
        val ItemsLigne5 = mList5[position]


       //     holder.startAutoScroll(holder.viewPager,1500,7000,1500)


            val adapterViewPager = Adapteur_image_home_slide(context,data_img_home_slide[position])
          //  val adapterViewPager2 = Adapteur_infini(adapterViewPager)
          //  holder.viewPager.adapter = adapterViewPager2
         //   holder.viewPager2.adapter = adapterViewPager2


            holder.tvoir.text = ItemsHome.toutVoir

            holder.Mnote.text = ItemsHome.mieuNote
            holder.tvoir2.text = ItemsHome.toutVoir

            holder.Récent.text = ItemsHome.recementConstruit
            holder.tvoir3.text = ItemsHome.toutVoir

            holder.populaire.text = ItemsHome.populaire
            holder.tvoir4.text = ItemsHome.toutVoir


            holder.imgLigne5.setImageResource(ItemsHome.image5)
            holder.textLigne5.text = ItemsHome.text51

            holder.img2Ligne5.setImageResource(ItemsHome.image52)
            holder.text2Ligne5.text = ItemsHome.text52

            holder.img3Ligne5.setImageResource(ItemsHome.image53)
            holder.text3Ligne5.text = ItemsHome.text53

            holder.img4Ligne5.setImageResource(ItemsHome.image54)
            holder.text4Ligne5.text = ItemsHome.text54

            holder.img5Ligne5.setImageResource(ItemsHome.image55)
            holder.text5Ligne5.text = ItemsHome.text55


            val adapter1 = CustomHomeAdapterItemLigne1(ItemsLigne1.itemLigne1)
            holder.ligne1.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
            holder.ligne1.adapter = adapter1

     //       val adapter2 = CustomHomeAdapterItemLigne2(eeItemsLigne2.itemLigne2)
       //     holder.ligne2.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
                //      holder.ligne2.adapter = adapter2

            val adapter3 = CustomHomeAdapterItemLigne3(ItemsLigne3.itemLigne3)
            holder.ligne3.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
            holder.ligne3.adapter = adapter3

            val adapter4 = CustomHomeAdapterItemLigne4(ItemsLigne4.itemLigne4)
            holder.ligne4.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
            holder.ligne4.adapter = adapter4

            val adapter5 = CustomHomeAdapterItemLigne5(ItemsLigne5.itemLigne5)
            holder.ligne5.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
            holder.ligne5.adapter = adapter5



            holder.ligne1.addOnItemTouchListener(MainActivity.RecyclerTouchListener(context,holder.ligne1,object :
                MainActivity.ClickListener {

                override fun onClick(view: View, position: Int) {
                    //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()
                    val ItemsLigne11 = ItemsLigne1.itemLigne1[position]
                    intent.putExtra("image", ItemsLigne11.image)
                    intent.putExtra("categori", ItemsLigne11.categorie )
                    intent.putExtra("lieux", ItemsLigne11.lieux )
                    intent.putExtra("ItemCategorie", ItemsLigne11.itemCategorie )
                    intent.putExtra("prix", ItemsLigne11.prix )

                    /*   val scaleXAnimator: ObjectAnimator =
                           ObjectAnimator.ofFloat<View>(holder.imgLigne5, View.SCALE_X, 3f)
                       val scaleYAnimator: ObjectAnimator =
                           ObjectAnimator.ofFloat<View>(holder.imgLigne5, View.SCALE_Y, 3f)
                       val animatorSet = AnimatorSet()
                       animatorSet.playTogether(scaleXAnimator, scaleYAnimator)
                       animatorSet.duration = 10000
                       animatorSet.start()
                       val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, holder.imgLigne5, "sharedImage")
                                        startActivity(context, intent, options.toBundle())
        */
                    startActivity(context, intent, Bundle.EMPTY)

                }

                override fun onLongClick(view: View?, position: Int) {

                }
            }))


            holder.ligne2.addOnItemTouchListener(MainActivity.RecyclerTouchListener(context,holder.ligne2,object :
                MainActivity.ClickListener {

                override fun onClick(view: View, position: Int) {
                    //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()

                    val ItemsLigne22 = ItemsLigne2.itemLigne2[position]
                    intent.putExtra("image", ItemsLigne22.image)
                    intent.putExtra("categori", ItemsLigne22.categorie )
                    intent.putExtra("lieux", ItemsLigne22.lieux )
                    intent.putExtra("ItemCategorie", ItemsLigne22.itemCategorie )
                    intent.putExtra("prix", ItemsLigne22.prix )

                    startActivity(context, intent, Bundle.EMPTY)


                }

                override fun onLongClick(view: View?, position: Int) {

                }
            }))


            holder.ligne3.addOnItemTouchListener(MainActivity.RecyclerTouchListener(context,holder.ligne3,object :
                MainActivity.ClickListener {

                override fun onClick(view: View, position: Int) {
                    //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()
                    val ItemsLigne33 = ItemsLigne3.itemLigne3[position]
                    intent.putExtra("image", ItemsLigne33.image)
                    intent.putExtra("categori", ItemsLigne33.categorie )
                    intent.putExtra("lieux", ItemsLigne33.lieux )
                    intent.putExtra("ItemCategorie", ItemsLigne33.itemCategorie )
                    intent.putExtra("prix", ItemsLigne33.prix )
                    startActivity(context, intent, Bundle.EMPTY)

                }

                override fun onLongClick(view: View?, position: Int) {

                }
            }))



            holder.ligne4.addOnItemTouchListener(MainActivity.RecyclerTouchListener(context,holder.ligne4,object :
                MainActivity.ClickListener {

                override fun onClick(view: View, position: Int) {
                    //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()
                    val ItemsLigne44 = ItemsLigne4.itemLigne4[position]
                    intent.putExtra("image", ItemsLigne44.image)
                    intent.putExtra("categori", ItemsLigne44.categorie )
                    intent.putExtra("lieux", ItemsLigne44.lieux )
                    intent.putExtra("ItemCategorie", ItemsLigne44.itemCategorie )
                    intent.putExtra("prix", ItemsLigne44.prix )
                    startActivity(context, intent, Bundle.EMPTY)

                }

                override fun onLongClick(view: View?, position: Int) {

                }
            }))

            holder.ligne5.addOnItemTouchListener(MainActivity.RecyclerTouchListener(context,holder.ligne5,object :
                MainActivity.ClickListener {

                override fun onClick(view: View, position: Int) {
                    //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()
                    val ItemsLigne55 = ItemsLigne5.itemLigne5[position]
                    intent.putExtra("image", ItemsLigne55.image)
                    intent.putExtra("categori", ItemsLigne55.categorie )
                    intent.putExtra("lieux", ItemsLigne55.lieux )
                    intent.putExtra("ItemCategorie", ItemsLigne55.itemCategorie )
                    intent.putExtra("prix", ItemsLigne55.prix )
                    startActivity(context, intent, Bundle.EMPTY)

                }

                override fun onLongClick(view: View?, position: Int) {

                }
            }))



      //  else{  onBindViewHolder(holder, nextPosition)}

//if(position!=0){holder.containerVp1.visibility = View.GONE}

/*
val animation = android.view.animation.AnimationUtils.loadAnimation(context,R.anim.fade_in)
        holder.container.startAnimation(animation)
*/
/*
if(position==0){


    } else{ holder.containerVp1.visibility = View.GONE

    holder.container.alpha=0f
    holder.container.animate().alpha(1f).setDuration(4000).start()
    }
*/


      //  holder.viewPager2.setCurrentItem(0,false)
        // sets the image to the imageview from our itemHolder class

       // holder.topQ.text = ItemsHome.topQualite




    }
/*
    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        holder.container.clearAnimation()
        super.onViewDetachedFromWindow(holder)
    }*/


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList0.size
    }


    // Holds the views for adding it to image and text
    @RequiresApi(Build.VERSION_CODES.M)
    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {

         val item1 : ConstraintLayout = itemView.findViewById(R.id.item1)


         val ligne1 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne1)
         val ligne2 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne2)
         val ligne3 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne3)
         val ligne4 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne4)
         val ligne5 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne5)


        val topQ: TextView = itemView.findViewById(R.id.topQ)
        val tvoir: TextView = itemView.findViewById(R.id.tvoir)

        val Mnote: TextView = itemView.findViewById(R.id.Mnote)
        val tvoir2: TextView = itemView.findViewById(R.id.tvoir2)

        val Récent: TextView = itemView.findViewById(R.id.Récent)
        val tvoir3: TextView = itemView.findViewById(R.id.tvoir3)


        val populaire: TextView = itemView.findViewById(R.id.populaire)
        val tvoir4: TextView = itemView.findViewById(R.id.tvoir4)

    //   val containerVp1 = itemView.findViewById<LinearLayout>(R.id.containerVp1)
       val container = itemView.findViewById<LinearLayout>(R.id.container)
   //     val viewPager = itemView.findViewById<ViewPager>(R.id.viewPager)
  //      val viewPager2 = itemView.findViewById<ViewPager>(R.id.viewPager2)


        val imgLigne5: ImageView = itemView.findViewById(R.id.imgLigne5)
        val img2Ligne5: ImageView = itemView.findViewById(R.id.img2Ligne5)
        val img3Ligne5: ImageView = itemView.findViewById(R.id.img3Ligne5)
        val img4Ligne5: ImageView = itemView.findViewById(R.id.img4Ligne5)
        val img5Ligne5: ImageView = itemView.findViewById(R.id.img5Ligne5)

        val textLigne5: TextView = itemView.findViewById(R.id.textLigne5)
        val text2Ligne5: TextView = itemView.findViewById(R.id.text2Ligne5)
        val text3Ligne5: TextView = itemView.findViewById(R.id.text3Ligne5)
        val text4Ligne5: TextView = itemView.findViewById(R.id.text4Ligne5)
        val text5Ligne5: TextView = itemView.findViewById(R.id.text5Ligne5)
        val text6Ligne5: TextView = itemView.findViewById(R.id.text6Ligne5)



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
