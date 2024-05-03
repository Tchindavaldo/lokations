package com.example.lokations


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.checkerframework.checker.units.qual.Length

class adapteur_recycleView_framelayout_home_data(private val type:ArrayList<String>,
                                                     private val data0: ArrayList<dataClass_img_home_slide>,
                                                     private val data1:ArrayList<ArrayList<ItemsLigne1Model>>,
                                                     private val data2:ArrayList<ArrayList<ItemsLigne2Model>>,
                                                     private val data3:ArrayList<ArrayList<ItemsLigne3Model>>,
                                                     private val data4:ArrayList<ArrayList<ItemsLigne4Model>>,
                                                     private val data5:ArrayList<ArrayList<ItemsLigne5Model>>,
                                                     private val data6: ArrayList<dataClass_img_home_slide>,
                                                     private val rv: RecyclerView,
                                                     private val posi: Int,
                        val context: Context
    ) : RecyclerView.Adapter<adapteur_recycleView_framelayout_home_data.ViewHolder>() {



    var dataIsBind = false
    var dataIsLoad = false
    var firstDataIsLoad = false
    var Isloading = false
val t1:Long =8000
val t2: Long =700
val topToShow =1170
val delay: Long = 0


        // create new views
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            // inflates the card_view_design view

            // that is used to hold list item
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.inflate_framelayout_home_data, parent, false)

            return ViewHolder(view)
        }

        // binds the list items to a view
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       //     holder.container.alpha = 0f


            holder.containerRv.alpha=0f

            holder.containerG2.alpha=0f
            holder.containerViewPager.alpha=0f
      /*      if(firstDataIsLoad && !dataIsBind) {

                holder.customAdapter(holder.viewPager)
                val adapterViewPager = Adapteur_image_home_slide(context, data0)
                val adapterViewPager2 = Adapteur_infini(adapterViewPager)
                holder.viewPager.adapter = adapterViewPager2
                holder.startAutoScroll(holder.viewPager, 1500, 7000, 1500)
                if (position != 0) {
                    holder.containerViewPager.visibility = View.GONE
                }

                holder.squelette.animate().alpha(0F).setDuration(400).start()
                holder.squelette.elevation=0f
                holder.containerRv.animate().alpha(1f).setDuration(400).start()
                holder.containerRv.elevation=20f

                dataIsBind=true
            }*/
            /*   holder.containerText.visibility = View.GONE
            holder.containerText.alpha = 0f
            holder.recycleView.alpha = 0f
                holder.container.animate().alpha(1f).setDuration(t1).start()
            val adapter0 = CustomHomeAdapterItemLigne0(context, data0)
            holder.recycleView.layoutManager = LinearLayoutManager(context)
            holder.recycleView.adapter = adapter0
            if (holder.recycleView.adapter != null) {

                holder.container.animate().alpha(1f).setDuration(t1).start()
                holder.containerText.animate().alpha(1f).setDuration(t1).start()
                holder.recycleView.animate().alpha(1f).setDuration(t2).start()
            }




            holder.text1Ligne2.text = "Meilleur Note"
            holder.text2Ligne2.text = "tout voir"


            holder.text1Ligne3.text = "Nouvoté"
            holder.text2Ligne3.text = "tout voir"
 */
          ///  holder.text1Ligne4.text = "populaire"
            //holder.text2Ligne4.text = "tout voir"

            //  holder.text1Linge5.text = "Bien Situé"



            // holder.text2Linge5.text = "tout voir"

            //  holder.recycleViewLigne3.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            // holder.recycleViewLigne4.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            //holder.recycleViewLigne5.layoutManager = LinearLayoutManager(context)

           holder.recycleViewLigne2.layoutManager =LinearLayoutManager(context)
            val adapter2 = CustomHomeAdapterItemLigne2(data0,data2[0],context)
            holder.recycleViewLigne2.adapter = adapter2

            val firstPosi = holder.recycleViewLigne2.layoutManager as LinearLayoutManager

            lateinit var currentLine: LinearLayout
            lateinit var nextLine: LinearLayout
            lateinit var firstView: LinearLayout

            lateinit var ligne2: LinearLayout
            lateinit var ligne3: LinearLayout
            lateinit var ligne4: LinearLayout
            lateinit var ligne5: LinearLayout
           // lateinit var  topCoordCurrentLine: Int

            var viewRecup: RecyclerView.ViewHolder? =null
            var viewRecupFirstPosi: RecyclerView.ViewHolder? =null
            var viewRecup2: RecyclerView.ViewHolder? =null
            var nextLineIsHide = true
            var dataIsAdd = false
            var rvIsNotify = false
            var rvPrevIsnotify = false
            var posiIsRecup = false
            var rvIsScroling = false

            var show = false

            var ligne2IsShow = false
            var ligne3IsShow = false
            var ligne4IsShow = false
            var ligne5IsShow = false

            var  ligne2IsHide=false
            var ligne3IsHide=false
            var ligne4IsHide=false
            var ligne5IsHide=false

            var posi = data2[0].size-1
            var posi2 = 0
            var nbrItem = 4
            var posiToInsert = data2[0].size
            var iter =1
            var addPosi =1


            var coordCurrentLine =Rect()
            var coordCurrentLine2 =Rect()
            var coordCurrentLine3 =Rect()
            var coordCurrentLine4 =Rect()
            var coordCurrentLine5 =Rect()
            var coordfirstLine =Rect()

            var topCoordCurrentLine = 0
            var topCoordCurrentLine2 = 0
            var topCoordCurrentLine3 = 0
            var topCoordCurrentLine4 = 0
            var topCoordCurrentLine5 = 0
            var topCoordFirstLine = 0
            var curent:String="ligne1"

            var dyy = 0
            val handlerx = Handler()













            rv.addOnScrollListener(object:RecyclerView.OnScrollListener(){
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

         dyy= dy


        val rvChild = (holder.recycleViewLigne2.layoutManager as LinearLayoutManager).onSaveInstanceState()
        //val rvState = recyclerView.layoutManager?.onSaveInstanceState()
if(!posiIsRecup){
    viewRecup =holder.recycleViewLigne2.findViewHolderForAdapterPosition(posi)
        nextLine = viewRecup!!.itemView.findViewById(R.id.ligne5)


    viewRecup2 =holder.recycleViewLigne2.findViewHolderForAdapterPosition(posi2)

        ligne2 = viewRecup2!!.itemView.findViewById(R.id.ligne2)
        ligne3 = viewRecup2!!.itemView.findViewById(R.id.ligne3)
        ligne4 = viewRecup2!!.itemView.findViewById(R.id.ligne4)
        ligne5 = viewRecup2!!.itemView.findViewById(R.id.ligne5)


    ligne2IsShow=false
    ligne3IsShow=false
    ligne4IsShow=false
    ligne5IsShow=false

    ligne2IsHide=false
    ligne3IsHide=false
    ligne4IsHide=false
    ligne5IsHide=false

    show=false
    dataIsLoad=false

    posiIsRecup=true
}

            nextLine.getGlobalVisibleRect(coordCurrentLine)
            topCoordCurrentLine = coordCurrentLine.top

            //    for(idx in 9 downTo 0){


        if(!firstDataIsLoad){




            val handler2 = Handler()
            handler2.postDelayed(
                {
                    Isloading=true

                            data2[0].removeAt(0)



                         adapter2.notifyDataSetChanged()
                    //  adapter2.notifyItemRemoved(0)


                    //   Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()
                }, 0//.toLong()
            )
            handlerx.postDelayed(
                {
                  //  Isloading=true
                  //  data2[0].removeAt(0)



                    for (ix in 0..4) {

                        data2[0].add(
                            ItemsLigne2Model(
                                R.drawable.m88,
                                R.drawable.m88,
                                R.drawable.plce,
                                "first data $iter ",
                                "chambre 3",
                                "300 000/ans",
                                "En cour de l'iberartion",
                                "3 chambre disponible",
                                "Douala",
                                R.drawable.fv,
                                R.drawable.fv,
                                R.drawable.fv
                            )
                        )
                        //  adapter2.onBindViewHolder(holder.recycleViewLigne2.findViewHolderForLayoutPosition(0) as CustomHomeAdapterItemLigne2.ViewHolder,ix)


                        iter++
                  //      adapter2.notifyItemInserted(ix)
                    }

                    //  adapter2.notifyItemInserted(posi)
                    // adapter2.notifyItemRangeInserted(posiToInsert,4)
                       adapter2.notifyDataSetChanged()
                    //posi = 4
                    nbrItem=4

                    posi = data2[0].size-1
                    posi2 = 1


                    posiIsRecup=false
                    firstDataIsLoad = true
                    Isloading=false

                    holder.customAdapter(holder.viewPager)
                    val adapterViewPager = Adapteur_image_home_slide(context, data0)
                    val adapterViewPager2 = Adapteur_infini(adapterViewPager)
                    holder.viewPager.adapter = adapterViewPager2
                    holder.startAutoScroll(holder.viewPager, 1500, 7000, 1500)


                    holder.squelette.animate().alpha(0F).setDuration(1500).start()
                    holder.containerRv.animate().alpha(1f).setDuration(1500).start()

                    holder.containerViewPager.animate().alpha(1f).setDuration(1500).start()
                    holder.containerG2.animate().alpha(1f).setDuration(1500).start()


          //   Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()
                }, 3000//.toLong()
            )


        }

        //   Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()


            //    Toast.makeText(context," top $topCoordCurrentLine2 ",Toast.LENGTH_LONG).show()

if(firstDataIsLoad){
if(!show){
            if (!ligne2IsShow) {
                ligne2.getGlobalVisibleRect(coordCurrentLine2)
                topCoordCurrentLine2 = coordCurrentLine2.top

            if (topCoordCurrentLine2 <= 1200 ) {
                ligne2.animate().alpha(1f).setDuration(1000).start()
                ligne2IsShow=true
            }
            }

            if (topCoordCurrentLine2 >= 1250 && !ligne2IsHide) {
                ligne2.alpha=0f
                ligne2IsHide=true
            }

            if ( !ligne3IsShow) {
                ligne3.getGlobalVisibleRect(coordCurrentLine3)
                topCoordCurrentLine3 = coordCurrentLine3.top
            if (topCoordCurrentLine3 <= topToShow ) {
                ligne3.animate().alpha(1f).setDuration(t2).start()
                ligne3IsShow=true
            }
            }
        if (topCoordCurrentLine3 >= 1250 && !ligne3IsHide) {
            ligne3.alpha=0f
            ligne3IsHide=true
        }

            if (!ligne4IsShow) {
                ligne4.getGlobalVisibleRect(coordCurrentLine4)
                topCoordCurrentLine4 = coordCurrentLine4.top
            if (topCoordCurrentLine4 <= topToShow ) {
                ligne4.animate().alpha(1f).setDuration(t2).start()
                ligne4IsShow=true
            }
            }

        if (topCoordCurrentLine4 >= 1250 && !ligne4IsHide) {
            ligne4.alpha=0f
            ligne4IsHide=true
        }

            if (!ligne5IsShow) {
                ligne5.getGlobalVisibleRect(coordCurrentLine5)
                topCoordCurrentLine5 = coordCurrentLine5.top

            if (topCoordCurrentLine5 <= topToShow) {
                ligne5.animate().alpha(1f).setDuration(t2).start()
                ligne5IsShow=true
                show=true
                   if(nbrItem>-1) {
                       posi2 = posi - nbrItem

                       nbrItem--

                       posiIsRecup=false
                   }
            }
            }

        if (topCoordCurrentLine5 >= 1250 && !ligne5IsHide) {
            ligne5.alpha=0f
            ligne5IsHide=true
        }
    }}

     /*   if(topCoordCurrentLine<=885 ) {
            if (rvIsNotify ) {
              //  posi = posi - 1

                viewRecup = holder.recycleViewLigne2.findViewHolderForAdapterPosition(posi)
                nextLine = viewRecup!!.itemView.findViewById(R.id.ligne5)

                nextLine.getGlobalVisibleRect(coordCurrentLine)
                topCoordCurrentLine = coordCurrentLine.top
             //   rvIsNotify = false
                //  holder.recycleViewLigne2.layoutManager?.scrollToPosition(0)
            }
        }*/
   /*     if(dy<0){
          //  Toast.makeText(context," top $topCoordFirstLine ",Toast.LENGTH_LONG).show()
            if(topCoordFirstLine<=269){
                if(!rvPrevIsnotify){
                        rv.stopScroll()
          /*          for(ix in 0 ..3) {
                        data2[0].removeAt(data2[0].size-1)

                    }*/

               //     for(ix in 0 ..3) {

                        data2[0].add(0, ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,
                            " prev","chambre 3","300 000/ans","En cour de l'iberartion",
                            "3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv)
                        )

                  //  adapter2.notifyItemInserted(0)

                  //  }

                    adapter2.notifyDataSetChanged()
                    rv.stopScroll()
              //      rv.smoothScrollToPosition(adapter2.itemCount-1)
                    rvPrevIsnotify=true



              /*      val handler = Handler()
                    handler.postDelayed(
                        {
                            for(ix in 0 ..3) {

                                data2[0].add(0, ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,
                                    "$ix prev","chambre 3","300 000/ans","En cour de l'iberartion",
                                    "3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv)
                                )



                            }

                            adapter2.notifyItemRangeInserted(0,3)

                            //  adapter2.notifyItemInserted(posi)
                            // adapter2.notifyItemRangeInserted(posiToInsert,4)
                         //   adapter2.notifyDataSetChanged()
                       //     rvPrevIsnotify=false
                            //   Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()
                        }, 3000//.toLong()
                    )*/
                }
            }
        }*/


            if(firstDataIsLoad){
                if(topCoordCurrentLine<=738){
                if(!rvIsNotify){

        //            rv.setOnTouchListener { v, event ->event.action==MotionEvent.ACTION_MOVE  }
          //          holder.recycleViewLigne2.setOnTouchListener { v, event ->event.action==MotionEvent.ACTION_MOVE  }
/*
rv.layoutManager=object :LinearLayoutManager(context){
    override fun canScrollVertically(): Boolean {
        return super.canScrollVertically().
    }
}*/
                    //val rvParent = rv.layoutManager?.onSaveInstanceState()


                    val size = data2[0].size
//data2[0].clear()
                    var posii = 0



                    val handler2 = Handler()
                    handler2.postDelayed(
                        {


                            if(data2[0].size-1>=9){

                                for(ix in 0 ..4) {
                                    data2[0].removeAt(0)

                                }
                                posii = 9

                            }else{
                                posii = posi + 5

                            }
                            adapter2.notifyDataSetChanged()
                            //   Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()
                        }, 400//.toLong()
                    )





                    val handler = Handler()
                    handler.postDelayed(
                        {
                            for(ix in 0 ..4) {

                                data2[0].add( ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,
                                    "$iter next","chambre 3","300 000/ans","En cour de l'iberartion",
                                    "3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv)
                                )
                                iter++
                        //        adapter2.onBindViewHolder(holder.recycleViewLigne2.findViewHolderForLayoutPosition(0) as CustomHomeAdapterItemLigne2.ViewHolder,data2[0].size)
                            }

                            //  adapter2.notifyItemInserted(posi)
                            // adapter2.notifyItemRangeInserted(posiToInsert,4)
                          adapter2.notifyDataSetChanged()

                            rvIsNotify=false
                            posi = posii

                            nbrItem=4
                            posi2 = posi - nbrItem
                            nbrItem--

                            dataIsLoad=true
                            posiIsRecup=false
                            rv.stopScroll()
                            holder.recycleViewLigne2.stopScroll()
                            //rv.isEnabled=true
                            //   Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()
                        }, 450//.toLong()
                    )



                    /*if( newState == RecyclerView.SCROLL_STATE_IDLE  ){
                        if( newState != RecyclerView.SCROLL_STATE_SETTLING){
                            if(  newState != RecyclerView.SCROLL_STATE_DRAGGING){


                            }

                        }
                    }*/
                    rvIsNotify=true
                }
            }
            }



        //  if(viewRecup!=null) {

      //  Toast.makeText(context," top $topCoordCurrentLine",Toast.LENGTH_LONG).show()
        /*   if (curent == "ligne1") {

               if (nextLineIsHide) {
                   viewRecup =holder.recycleViewLigne2.findViewHolderForAdapterPosition(posi)
                   nextLine = viewRecup!!.itemView.findViewById(R.id.ligne2)
                      nextLine.alpha = 1f
                   nextLine.animate().alpha(1f).setDuration(t2).start()

                   curent = "ligne2"
                   currentLine = nextLine
                   nextLineIsHide = false
               }

           }
           if (curent == "ligne2") {

               nextLineIsHide = true
               currentLine.getGlobalVisibleRect(coordCurrentLine)
               val topCoordCurrentLine = coordCurrentLine.top

               if (topCoordCurrentLine <= 850 && nextLineIsHide) {
                   nextLine = viewRecup!!.itemView.findViewById(R.id.ligne3)
                   //   nextLine.alpha = 1f
                   //nextLine.animate().alpha(1f).setDuration(t2).setStartDelay(delay).start()
                   data2[0].add( ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,
                       "cité rose","chambre 3","300 000/ans","En cour de l'iberartion",
                       "3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv)
                   )

                   adapter2.notifyItemInserted(data2.size)
                   curent = "ligne3"
                   currentLine = nextLine
                   nextLineIsHide = false
               }

           }
           if (curent == "ligne3") {

               nextLineIsHide = true
               currentLine.getGlobalVisibleRect(coordCurrentLine)
               val topCoordCurrentLine = coordCurrentLine.top


               if (  nextLineIsHide) {
                   nextLine = viewRecup!!.itemView.findViewById(R.id.ligne4)
                   //   nextLine.alpha = 1f topCoordCurrentLine <= 570
                //   nextLine.animate().alpha(1f).setDuration(t2).setStartDelay(delay).start()

                   curent = "ligne4"
                   currentLine = nextLine
                   nextLineIsHide = false

               }

           }
           if (curent == "ligne4") {

               nextLineIsHide = true
               currentLine.getGlobalVisibleRect(coordCurrentLine)
               val topCoordCurrentLine = coordCurrentLine.top


               if (topCoordCurrentLine <= 530 && nextLineIsHide) {
                   nextLine = viewRecup!!.itemView.findViewById(R.id.ligne5)
                   //   nextLine.alpha = 1f
                       //    nextLine.animate().alpha(1f).setDuration(t2).setStartDelay(0).start()

                   curent = "ligne5"
                   currentLine = nextLine
                   nextLineIsHide = false
               }

           }
           if (curent == "ligne5") {

               nextLineIsHide = true
               currentLine.getGlobalVisibleRect(coordCurrentLine)
               topCoordCurrentLine = coordCurrentLine.top
              // Toast.makeText(context," top coord $topCoordCurrentLine",Toast.LENGTH_LONG).show()

           if(topCoordCurrentLine<=888 && nextLineIsHide){

               if(posi<1){


                   val handler = Handler()
                   handler.postDelayed(
                       {
                       },100
                   )
                   posi += 1
                   viewRecup =holder.recycleViewLigne2.findViewHolderForAdapterPosition(posi)

                   nextLine = viewRecup!!.itemView.findViewById(R.id.ligne2)
               //    nextLine.alpha = 1f
               //    nextLine.animate().alpha(1f).setDuration(t2).start()
                   //   nextLine.alpha = 1f
                   //       nextLine.animate().alpha(1f).setDuration(t2).setStartDelay(delay).start()

                   curent="ligne2"
                   currentLine=nextLine
                   nextLineIsHide = false
               }

            //  }else{ Toast.makeText(context," recup viex pas encore pas effectué",Toast.LENGTH_LONG).show()}
           }

               /*            val ligne3: LinearLayout = viewRecup!!.itemView.findViewById(R.id.ligne3)
                           val ligne4: LinearLayout = viewRecup!!.itemView.findViewById(R.id.ligne4)
                           val ligne5: LinearLayout = viewRecup!!.itemView.findViewById(R.id.ligne5)
                           if (topCoordCurrentLine <= 738 && !rvIsNotify) {
                               data2[0].add( ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,
                                   "cité rose","chambre 3","300 000/ans","En cour de l'iberartion",
                                   "3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv)
                               )

                               ligne3.alpha = 1f
                               ligne4.alpha = 1f
                               ligne5.alpha = 1f
                               adapter2.notifyDataSetChanged()

                               rvIsNotify=true

                 /*             val handler = Handler()
                               handler.postDelayed(
                                   { adapter2.notifyDataSetChanged()

                                       Toast.makeText(context," ajouté lors de l'arret",Toast.LENGTH_LONG).show()



                                   },500
                               )*/
                               //  rvIsNotify=false
                           }
           */
           }*/
       // }else{ Toast.makeText(context," posi pas dispo",Toast.LENGTH_LONG).show()
       // }


    }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                    rvIsScroling = newState == RecyclerView.SCROLL_STATE_IDLE


                            if(Isloading){

                                if (newState != RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_IDLE || dyy>0 || dyy<0) {
                                    //      if(  newState != RecyclerView.SCROLL_STATE_DRAGGING){


                                    rv.stopScroll()
                                    holder.recycleViewLigne2.stopScroll()

                                }

                                if (newState == RecyclerView.SCROLL_STATE_SETTLING ) {
                                    //      if(  newState != RecyclerView.SCROLL_STATE_DRAGGING){


                                    rv.stopScroll()
                                    holder.recycleViewLigne2.stopScroll()

                                }
                                if (newState != RecyclerView.SCROLL_STATE_SETTLING ) {
                                    //      if(  newState != RecyclerView.SCROLL_STATE_DRAGGING){


                                    rv.stopScroll()
                                    holder.recycleViewLigne2.stopScroll()

                                }

                                if ( newState == RecyclerView.SCROLL_STATE_IDLE) {
                                    //      if(  newState != RecyclerView.SCROLL_STATE_DRAGGING){


                                    rv.stopScroll()
                                    holder.recycleViewLigne2.stopScroll()

                                }

                                if ( newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                                    //      if(  newState != RecyclerView.SCROLL_STATE_DRAGGING){


                                    rv.stopScroll()
                                    holder.recycleViewLigne2.stopScroll()

                                }




                            }

                            //   Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()



                    if(firstDataIsLoad ) {

                        if (topCoordCurrentLine<=738 && !dataIsLoad) {
                            //  if( newState == RecyclerView.SCROLL_STATE_IDLE  ){
                            if (newState != RecyclerView.SCROLL_STATE_SETTLING) {
                                //      if(  newState != RecyclerView.SCROLL_STATE_DRAGGING){


                                rv.stopScroll()
                                holder.recycleViewLigne2.stopScroll()

                            }
                        }

                        //   }
                    }
                           }
                       //}


            })


        /*        if(topCoordLigne3<=200 ){

                    val rvRectLigne4 =Rect()
                    val topCoordLigne4 = rvRectLigne4.top
                    holder.recycleViewLigne4.getGlobalVisibleRect(rvRectLigne4)
                    Toast.makeText(context," topcoord ligne4 est $topCoordLigne4 et coordligne4 $rvRectLigne4",Toast.LENGTH_LONG).show()

                    if(topCoordLigne4<=200 ){

                        val rvRectLigne5 =Rect()
                        val topCoordLigne5 = rvRectLigne5.top
                        holder.recycleViewLigne5.getGlobalVisibleRect(rvRectLigne5)
                        Toast.makeText(context," top coord 5 est $topCoordLigne5 et coordligne5 $rvRectLigne5",Toast.LENGTH_LONG).show()

                        if(topCoordLigne5<=200 ){

                            val rvRectnextRv =Rect()
                            val topCoordLnextRv = rvRectnextRv.top
                            holder.recycleViewNextData.getGlobalVisibleRect(rvRectnextRv)
                            Toast.makeText(context," top coord nextRv est $topCoordLnextRv et coordnextRv $rvRectnextRv",Toast.LENGTH_LONG).show()
                        }
                    }
                } */

     /*   if(posi<2  && topCoord<=500 ){
            holder.recycleViewNextData.layoutManager =LinearLayoutManager(context)
            val adapterNextData = adapteur_recycleView_framelayout_home_data(type,data0,data1,data2,data3,data4,data5,data6,
                holder.recycleViewNextData,2,context)
            holder.recycleViewNextData.adapter = adapterNextData
        }*/


            /*           holder.recycleViewLigne3.layoutManager =LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                       val adapter3 = CustomHomeAdapterItemLigne3(data3[1])
                       holder.recycleViewLigne3.adapter = adapter3

                       holder.recycleViewLigne4.layoutManager =LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                       val adapter4 = CustomHomeAdapterItemLigne4(data4[1])
                       holder.recycleViewLigne4.adapter = adapter4

                       holder.recycleViewLigne5.layoutManager =LinearLayoutManager(context)
                       val adapter6 = CustomHomeAdapterItemLigne6(context,data6)
                       holder.recycleViewLigne5.adapter = adapter6
           */

            val screenHeight = Resources.getSystem().displayMetrics.heightPixels
            val poA7 = screenHeight * 0.7
            val poA5 = screenHeight * 0.5

            /*rv.addOnScrollListener(object:RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val rvRectLigne5 =Rect()
                    holder.recycleViewLigne4.getGlobalVisibleRect(rvRectLigne5)
                    val top =rvRectLigne5.top

                    Toast.makeText(context," top $top et coordligne5 $rvRectLigne5",Toast.LENGTH_LONG).show()

                   val rectTOVerify = Rect(0,850,-720,1318)
                    if(rvRectLigne5.top<=852 ){
                        Toast.makeText(context," top atteint $top et coordligne5 $rvRectLigne5",Toast.LENGTH_LONG).show()

                        if(posi<2){
                        holder.recycleViewNextData.layoutManager =LinearLayoutManager(context)
                        val adapterNextData = adapteur_recycleView_framelayout_home_data(type,data0,data1,data2,data3,data4,data5,data6,
                            holder.recycleViewNextData,2,context)
                        holder.recycleViewNextData.adapter = adapterNextData
                    }
                }

                    /*        if(topCoordLigne3<=200 ){

                                val rvRectLigne4 =Rect()
                                val topCoordLigne4 = rvRectLigne4.top
                                holder.recycleViewLigne4.getGlobalVisibleRect(rvRectLigne4)
                                Toast.makeText(context," topcoord ligne4 est $topCoordLigne4 et coordligne4 $rvRectLigne4",Toast.LENGTH_LONG).show()

                                if(topCoordLigne4<=200 ){

                                    val rvRectLigne5 =Rect()
                                    val topCoordLigne5 = rvRectLigne5.top
                                    holder.recycleViewLigne5.getGlobalVisibleRect(rvRectLigne5)
                                    Toast.makeText(context," top coord 5 est $topCoordLigne5 et coordligne5 $rvRectLigne5",Toast.LENGTH_LONG).show()

                                    if(topCoordLigne5<=200 ){

                                        val rvRectnextRv =Rect()
                                        val topCoordLnextRv = rvRectnextRv.top
                                        holder.recycleViewNextData.getGlobalVisibleRect(rvRectnextRv)
                                        Toast.makeText(context," top coord nextRv est $topCoordLnextRv et coordnextRv $rvRectnextRv",Toast.LENGTH_LONG).show()
                                    }
                                }
                            } */

                 /*   if(posi<2  && topCoord<=500 ){
                        holder.recycleViewNextData.layoutManager =LinearLayoutManager(context)
                        val adapterNextData = adapteur_recycleView_framelayout_home_data(type,data0,data1,data2,data3,data4,data5,data6,
                            holder.recycleViewNextData,2,context)
                        holder.recycleViewNextData.adapter = adapterNextData
                    }*/
                }
            }  )*/
         //   val rvIsVisible =  holder.recycleViewLigne3.getGlobalVisibleRect(rvRect)
        /*    if(rvRect.top<=bottomposition ){

                Toast.makeText(context,"posidd inferieur a 0.7 est bottom  binder $rvRect",Toast.LENGTH_LONG).show()
            }
            if(rvRect.top<=screenHeight * 0.4 ){

                Toast.makeText(context,"posi inferieur a 0.4 est bottom  binder $rvRect",Toast.LENGTH_LONG).show()
            }
            if(rvRect.top<=screenHeight * 0.2 ){

                Toast.makeText(context,"inferieur a 0.2 est bottom  binder $rvRect",Toast.LENGTH_LONG).show()
            }*/
         //   holder.recycleViewNextData.visibility=View.GONE


      //      holder.container.animate().alpha(1f).setDuration(t2).start()
        }
        // return the number of the items in the list
        override fun getItemCount(): Int {
            return 1
        }


        // Holds the views for adding it to image and text
        @RequiresApi(Build.VERSION_CODES.M)
        class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {
            val viewPager = itemView.findViewById<ViewPager>(R.id.viewPager)
            val containerViewPager = itemView.findViewById<LinearLayout>(R.id.containerViewPager)

            val recycleViewLigne2 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne2)
            /*       val recycleViewLigne3 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne3)
                   val recycleViewLigne4 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne4)
                   val recycleViewLigne5 : RecyclerView = itemView.findViewById(R.id.recycleViewLigne5)
                   val recycleViewNextData : RecyclerView = itemView.findViewById(R.id.recycleViewNextData)
       */
            val container : ConstraintLayout = itemView.findViewById(R.id.container)
            val containerRv : LinearLayout = itemView.findViewById(R.id.containerRv)
            val containerG2 : LinearLayout = itemView.findViewById(R.id.containerG2)
            val squelette : LinearLayout = itemView.findViewById(R.id.squelette)
        //    val containerText : LinearLayout = itemView.findViewById(R.id.containerText)
            //      val text1Ligne2 : TextView = itemView.findViewById(R.id.text1Ligne2)
            //     val text2Ligne2 : TextView = itemView.findViewById(R.id.text2Ligne2)

          //  val text1Ligne3 : TextView = itemView.findViewById(R.id.text1Ligne3)
        //    val text2Ligne3 : TextView = itemView.findViewById(R.id.text2Ligne3)

         //   val text1Ligne4 : TextView = itemView.findViewById(R.id.text1Ligne4)
        //    val text2Ligne4 : TextView = itemView.findViewById(R.id.text2Ligne4)

            /*
            val text1Ligne4 : TextView = itemView.findViewById(R.id.text1Ligne4)

            val text2Ligne4 : TextView = itemView.findViewById(R.id.text2Ligne4)

            val viewPager = itemView.findViewById<ViewPager>(R.id.viewPager)
          //  val viewPager2 = itemView.findViewById<ViewPager>(R.id.viewPager2)
*/
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


/*      if(posi==1){val n :LinearLayout = viewRecup!!.itemView.findViewById(R.id.ligne5)
                 n.alpha = 1f
                 n.animate().alpha(1f).setDuration(0).setStartDelay(0).start()} */
/*       data2[0].removeAt(1)
                   adapter2.notifyDataSetChanged()

                    data2[0].removeAt(2)
                   adapter2.notifyDataSetChanged()


                   data2[0].removeAt(3)
                   adapter2.notifyDataSetChanged()*/
//        adapter2.notifyItemRemoved(0)
/*
                     viewRecup2 =holder.recycleViewLigne2.findViewHolderForAdapterPosition(0)
                     nextLine2 = viewRecup2!!.itemView.findViewById(R.id.container)
                     nextLine2.translationY = 385f*/

//                   adapter2.notifyItemInserted(posi+1)
//     adapter2.notifyItemRangeInserted(1,2)
//adapter2.notifyItemRangeRemoved(0,4)

//     recyclerView.layoutManager?.onRestoreInstanceState(rvState)
//  holder.recycleViewLigne2.layoutManager?.scrollToPosition(0)
//recyclerView.layoutManager?.onRestoreInstanceState(rvParent)
//        recyclerView.layoutManager?.onRestoreInstanceState(rvChild)
//
//  Toast.makeText(context," ajout ",Toast.LENGTH_LONG).show()

//    curent="ligne1"

