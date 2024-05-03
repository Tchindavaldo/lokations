package com.example.lokations

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class
HomeFragment  : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclervieww = view.findViewById<RecyclerView>(R.id.recyclerviewHomme)
        val connexion : LinearLayout = view.findViewById(R.id.item_categori)
        val iconChambre : LinearLayout = view.findViewById(R.id.iconChambre)

        connexion.setOnClickListener { val it =  Intent(requireContext(), GetTest2Activity::class.java); startActivity(it) }
        iconChambre.setOnClickListener { val it =  Intent(requireContext(), activity_recycleViewFragment::class.java); startActivity(it) }


/*

        val listdata1 = ArrayList<ItemsLigne1>()
        val listdata2 = ArrayList<ItemsLigne2>()
        val listdata3 = ArrayList<ItemsLigne3>()
        val listdata4 = ArrayList<ItemsLigne4>()
        val listdata5 = ArrayList<ItemsLigne5>()


        val dataHome = ArrayList<itemSHomeModel>()

        val data1 = ArrayList<ItemsLigne1Model>()
        val data2 = ArrayList<ItemsLigne2Model>()
        val data3 = ArrayList<ItemsLigne3Model>()
        val data4 = ArrayList<ItemsLigne4Model>()
        val data5 = ArrayList<ItemsLigne5Model>()


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))

        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))

        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))


        dataHome.add(itemSHomeModel(R.drawable.m88, "tout voir","Top Qualité" ,"Meilleur Note", "Recment Construit", "Populaire",
            R.drawable.m88,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
            "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))

        data1.add(ItemsLigne1Model(R.drawable.m88,"appartement","appartement","ngousso","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data1.add(ItemsLigne1Model(R.drawable.m88,"appartement","appartement","ngousso2","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data1.add(ItemsLigne1Model(R.drawable.m88,"appartement","appartement","ngousso3","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))

        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 1","200 000/ans","En cour de l'iberartion","3 chambre disponible","200 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 2","250 000/ans","En cour de l'iberartion","3 chambre disponible","250 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","300 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))

        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo2","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo3","Maison","Maison","Note","300 000/ans"))

        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))

        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre1","bangante","150 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre4","bangante","180 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre6","bangante","100 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre7","bangante", "200 000/ans"))

        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))
        listdata1.add(ItemsLigne1(data1))

        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))
        listdata2.add(ItemsLigne2(data2))

        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))
        listdata3.add(ItemsLigne3(data3))

        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))
        listdata4.add(ItemsLigne4(data4))

        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))
        listdata5.add(ItemsLigne5(data5))

        val itemList = ArrayList<dataClass_img_home_slide>()
        val arrayItemList = ArrayList<ArrayList<dataClass_img_home_slide>>()
        itemList.add(dataClass_img_home_slide(R.drawable.m4,"cité niva","1 chambre en cour de l'iberartion, 1 chambre disponible"))
        itemList.add(dataClass_img_home_slide(R.drawable.m7,"cité Rose","3 chambre en cour de l'iberartion, 3 chambre disponible"))
        itemList.add(dataClass_img_home_slide(R.drawable.m88,"cité Des Anges","2 chambre en cour de l'iberartion, 6 chambre disponible"))
        itemList.add(dataClass_img_home_slide(R.drawable.m91,"cité Hypocrate","5 chambre en cour de l'iberartion, 2 chambre disponible"))

        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)
        arrayItemList.add(itemList)

*/
        val data = ArrayList<String>()

        data.add("ligne2")
        data.add("ligne4")
        data.add("ligne6")
        data.add("ligne3")



        val data1 = ArrayList<ItemsLigne1Model>()
        val listData1 = ArrayList<ArrayList<ItemsLigne1Model>>()

        data1.add(ItemsLigne1Model(R.drawable.m88,"appartement","appartement","ngousso","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data1.add(ItemsLigne1Model(R.drawable.m88,"appartement","appartement","ngousso2","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data1.add(ItemsLigne1Model(R.drawable.m88,"appartement","appartement","ngousso3","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data1.add(ItemsLigne1Model(R.drawable.m88,"appartement","appartement","ngousso3","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))


        listData1.add(data1)
        listData1.add(data1)
        listData1.add(data1)
        listData1.add(data1)
        listData1.add(data1)


        val data3 = ArrayList<ItemsLigne3Model>()
        val listData3 = ArrayList<ArrayList<ItemsLigne3Model>>()

        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo","Maison","Maison","Note","300 000"))

        listData3.add(data3)
        listData3.add(data3)
        listData3.add(data3)
        listData3.add(data3)
        listData3.add(data3)

        val data4 = ArrayList<ItemsLigne4Model>()
        val listData4 = ArrayList<ArrayList<ItemsLigne4Model>>()

        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"cité","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"cité","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"cité","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"cité","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))

        listData4.add(data4)
        listData4.add(data4)
        listData4.add(data4)
        listData4.add(data4)
        listData4.add(data4)

        val data2 = ArrayList<ItemsLigne2Model>()
        val listData2 = ArrayList<ArrayList<ItemsLigne2Model>>()


      //  data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"beni city","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv))
  /*      data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité mandela","chambre 1","200 000/ans","En cour de l'iberartion","3 chambre disponible","Bafoussam",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité verte","chambre 2","250 000/ans","En cour de l'iberartion","3 chambre disponible","Baganté",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"beni city","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","Dschang",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"beni city endline","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv))
*/

        data2.add(ItemsLigne2Model(null,R.drawable.m88,R.drawable.plce,"beni city","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv))
   /*     data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité mandela","chambre 1","200 000/ans","En cour de l'iberartion","3 chambre disponible","Bafoussam",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité verte","chambre 2","250 000/ans","En cour de l'iberartion","3 chambre disponible","Baganté",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"beni city endline10","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","Douala",R.drawable.fv,R.drawable.fv,R.drawable.fv))
*/

        listData2.add(data2)
        listData2.add(data2)
        listData2.add(data2)
        listData2.add(data2)
        listData2.add(data2)

        val data5 = ArrayList<ItemsLigne5Model>()
        val listData5 = ArrayList<ArrayList<ItemsLigne5Model>>()

        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre1","bangante","150 000"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre4","bangante","180 000"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre6","bangante","100 000"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine endLine","chambre7","bangante", "200 000"))

        listData5.add(data5)
        listData5.add(data5)
        listData5.add(data5)
        listData5.add(data5)
        listData5.add(data5)

        val itemList = ArrayList<dataClass_img_home_slide>()
       // itemList.add(dataClass_img_home_slide(R.drawable.m4,"cité niva","1 chambre en cour de l'iberartion, 1 chambre disponible"))
        itemList.add(dataClass_img_home_slide(R.drawable.m7,"cité Rose","3 chambre en cour de l'iberartion, 3 chambre disponible"))

        val adapter2 = CustomHomeAdapterItemLigne2(itemList,data2,requireContext())

        val adapter = adapteur_recycleView_framelayout_home_data(data,itemList,listData1,listData2,listData3,listData4,listData5,itemList,
            recyclervieww,0, requireContext())

        recyclervieww.layoutManager = LinearLayoutManager(requireContext())
        recyclervieww.adapter = adapter



        val intent: Intent = Intent(requireContext(), FrameLayoutActivity::class.java)

    //    val adapter = CustomAdapterHome(recyclervieww,dataHome,listdata1, listdata2,listdata3,listdata4,listdata5,arrayItemList, requireContext(), "detail_cite", intent)

    //    recyclervieww.itemAnimator=DefaultItemAnimator()
      //  recyclervieww.adapter = adapter
        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment ().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    interface ClickListener {
        fun onClick(view: View, position: Int)

        fun onLongClick(view: View?, position: Int)
    }

    internal class RecyclerTouchListener(
        context: Context,
        recyclerView: RecyclerView,
        private val clickListener: ClickListener?
    ) : RecyclerView.OnItemTouchListener {

        private val gestureDetector: GestureDetector

        init {
            gestureDetector =
                GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
                    override fun onSingleTapUp(e: MotionEvent): Boolean {
                        return true
                    }

                    override fun onLongPress(e: MotionEvent) {
                        val child = recyclerView.findChildViewUnder(e.x, e.y)
                        if (child != null && clickListener != null) {
                            clickListener.onLongClick(child, recyclerView.getChildPosition(child))
                        }
                    }
                })
        }

        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

            val child = rv.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child))
            }
            return false
        }

        override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

        override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

        }
    }
}