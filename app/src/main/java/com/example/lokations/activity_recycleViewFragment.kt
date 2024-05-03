package com.example.lokations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class activity_recycleViewFragment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view_fragment)

      val recyclervieww = findViewById<RecyclerView>(R.id.recyclerviewHomme)

        val data = ArrayList<String>()

        data.add("ligne0")
        data.add("ligne1")
        data.add("ligne2")
        data.add("ligne3")
        data.add("ligne4")
        data.add("ligne5")
        data.add("ligne6")

        data.add("ligne0")
        data.add("ligne1")
        data.add("ligne2")
        data.add("ligne3")
        data.add("ligne4")
        data.add("ligne5")
        data.add("ligne6")

        data.add("ligne0")
        data.add("ligne1")
        data.add("ligne2")
        data.add("ligne3")
        data.add("ligne4")
        data.add("ligne5")
        data.add("ligne6")

        data.add("ligne0")
        data.add("ligne1")
        data.add("ligne2")
        data.add("ligne3")
        data.add("ligne4")
        data.add("ligne5")
        data.add("ligne6")

        data.add("ligne0")
        data.add("ligne1")
        data.add("ligne2")
        data.add("ligne3")
        data.add("ligne4")
        data.add("ligne5")
        data.add("ligne6")


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

        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo2","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo3","Maison","Maison","Note","300 000/ans"))
        data3.add(ItemsLigne3Model(R.drawable.m88,R.drawable.plce,"bonandjo3","Maison","Maison","Note","300 000/ans"))

        listData3.add(data3)
        listData3.add(data3)
        listData3.add(data3)
        listData3.add(data3)
        listData3.add(data3)

        val data4 = ArrayList<ItemsLigne4Model>()
        val listData4 = ArrayList<ArrayList<ItemsLigne4Model>>()

        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data4.add(ItemsLigne4Model(R.drawable.m88,R.drawable.favp,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))

        listData4.add(data4)
        listData4.add(data4)
        listData4.add(data4)
        listData4.add(data4)
        listData4.add(data4)

        val data2 = ArrayList<ItemsLigne2Model>()
        val listData2 = ArrayList<ArrayList<ItemsLigne2Model>>()

        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 1","200 000/ans","En cour de l'iberartion","3 chambre disponible","200 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))
        data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 2","250 000/ans","En cour de l'iberartion","3 chambre disponible","250 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))
       data2.add(ItemsLigne2Model(R.drawable.m88,R.drawable.m88,R.drawable.plce,"cité","chambre 3","300 000/ans","En cour de l'iberartion","3 chambre disponible","300 000/ans",R.drawable.fv,R.drawable.fv,R.drawable.fv))


        listData2.add(data2)
        listData2.add(data2)
        listData2.add(data2)
        listData2.add(data2)
        listData2.add(data2)

        val data5 = ArrayList<ItemsLigne5Model>()
        val listData5 = ArrayList<ArrayList<ItemsLigne5Model>>()

        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre1","bangante","150 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre4","bangante","180 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre6","bangante","100 000/ans"))
        data5.add(ItemsLigne5Model(R.drawable.m88,"cite reine","chambre7","bangante", "200 000/ans"))

        listData5.add(data5)
        listData5.add(data5)
        listData5.add(data5)
        listData5.add(data5)
        listData5.add(data5)

      val itemList = ArrayList<dataClass_img_home_slide>()
      itemList.add(dataClass_img_home_slide(R.drawable.m4,"cité niva","1 chambre en cour de l'iberartion, 1 chambre disponible"))
      itemList.add(dataClass_img_home_slide(R.drawable.m7,"cité Rose","3 chambre en cour de l'iberartion, 3 chambre disponible"))
      itemList.add(dataClass_img_home_slide(R.drawable.m88,"cité Des Anges","2 chambre en cour de l'iberartion, 6 chambre disponible"))
      itemList.add(dataClass_img_home_slide(R.drawable.m91,"cité Hypocrate","5 chambre en cour de l'iberartion, 2 chambre disponible"))



      val adapter = adapteur_recycleView_framelayout_home_data(data,itemList,listData1,listData2,listData3,listData4,listData5,itemList,
          recyclervieww    ,0,this)

        recyclervieww.layoutManager = LinearLayoutManager(this)
        recyclervieww.adapter = adapter

    }
}