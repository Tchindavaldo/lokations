

// package com.example.mmm
//
// import android.content.Context
// import android.content.Intent
// import androidx.appcompat.app.AppCompatActivity
// import android.os.Bundle
// import android.view.GestureDetector
// import android.view.MotionEvent
// import android.view.View
// import androidx.constraintlayout.widget.ConstraintLayout
// import androidx.recyclerview.widget.LinearLayoutManager
// import androidx.recyclerview.widget.RecyclerView
// import com.google.android.material.navigation.NavigationBarItemView
// import com.google.android.material.tabs.TabLayout
//
// class copieHomeActivity : AppCompatActivity() {
//
// lateinit var intent2: Intent
//
// override fun onCreate(savedInstanceState: Bundle?) {
// super.onCreate(savedInstanceState)
// setContentView(R.layout.home)
//
// val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
//
// val recyclervieww = findViewById<RecyclerView>(R.id.recyclerviewHomme)
// val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
//
// val COntaier_recyclerView_search: ConstraintLayout = findViewById(R.id.COntaier_recyclerView_search)
//
// val p1 : NavigationBarItemView = findViewById(R.id.page_1)
// val p2 : NavigationBarItemView = findViewById(R.id.page_2)
// val p3 : NavigationBarItemView = findViewById(R.id.page_3)
// val p5 : NavigationBarItemView = findViewById(R.id.page_5)
//
// p1.setOnClickListener {
//
// recyclervieww.visibility = View.VISIBLE
// recyclervieww.elevation = 5f
//
// COntaier_recyclerView_search.visibility = View.GONE
// COntaier_recyclerView_search.elevation = 0f
//
// tabLayout.visibility = View.GONE
// tabLayout.elevation = 0f
// }
//
// p2.setOnClickListener {
//
// COntaier_recyclerView_search.visibility = View.VISIBLE
// COntaier_recyclerView_search.elevation = 5f
//
// tabLayout.visibility = View.VISIBLE
// tabLayout.elevation = 5f
//
// recyclervieww.visibility = View.GONE
// recyclervieww.elevation = 0f
// }
//
// p3.setOnClickListener {
//
// val intent: Intent = Intent(this@copieHomeActivity, MikelActivity::class.java)
//
// startActivity(intent)
//
// }
//
//
// p5.setOnClickListener {
//
// val intent: Intent = Intent(this@copieHomeActivity, FrameLayoutActivity::class.java)
//
// startActivity(intent)
//
// }
//
// val listdataHome = ArrayList<HomeModel>()
//
//
//
// //   viewPager = findViewById<ViewPager>(R.id.viewPager)
//
// tabLayout.addTab(tabLayout.newTab().setText("prix"))
// tabLayout.addTab(tabLayout.newTab().setText("klity"))
// tabLayout.addTab(tabLayout.newTab().setText("comment"))
// tabLayout.addTab(tabLayout.newTab().setText("ville"))
// tabLayout.addTab(tabLayout.newTab().setText("position"))
// tabLayout.addTab(tabLayout.newTab().setText("like"))
// tabLayout.addTab(tabLayout.newTab().setText("visite"))
// tabLayout.addTab(tabLayout.newTab().setText("prix"))
// tabLayout.addTab(tabLayout.newTab().setText("klity"))
// tabLayout.addTab(tabLayout.newTab().setText("comment"))
//
//
// recyclerview.layoutManager = LinearLayoutManager(this)
//
// val data = ArrayList<ItemsViewModel>()
// val dataa = ArrayList<ItemsViewModell>()
// val data2Search = ArrayList<ItemsViewModel2>()
// val data3Search = ArrayList<ItemsViewModel3>()
//
//
// val listdata1 = ArrayList<ItemsLigne1>()
// val listdata2 = ArrayList<ItemsLigne2>()
// val listdata3 = ArrayList<ItemsLigne3>()
// val listdata4 = ArrayList<ItemsLigne4>()
// val listdata5 = ArrayList<ItemsLigne5>()
//
//
//
//
// val dataHome = ArrayList<itemSHomeModel>()
//
// val data1 = ArrayList<ItemsLigne1Model>()
// val data2 = ArrayList<ItemsLigne2Model>()
// val data3 = ArrayList<ItemsLigne3Model>()
// val data4 = ArrayList<ItemsLigne4Model>()
// val data5 = ArrayList<ItemsLigne5Model>()
//
//
//
// dataHome.add(itemSHomeModel(R.drawable.m7, "tout voir","Top Qualité" ,"Mieux Noé", "Recment Construit", "Populaire",
// R.drawable.m3,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
// "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))
//
// dataHome.add(itemSHomeModel(R.drawable.m7, "tout voir","Top Qualité" ,"Mieux Noé", "Recment Construit", "Populaire",
// R.drawable.m3,  R.drawable.plce, R.drawable.favp, R.drawable.fv, R.drawable.comment ,
// "Appartement","Yaoundé, Cité Verte","22","52","10 Commentaires","Salon: 4m*4m*2m, Chambre: 3m*3m*2m"   ))
//
// data1.add(ItemsLigne1Model(R.drawable.m1,"appartement","appartement","ngousso","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
// data1.add(ItemsLigne1Model(R.drawable.m2,"appartement","appartement","ngousso2","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
// data1.add(ItemsLigne1Model(R.drawable.m3,"appartement","appartement","ngousso3","70 000/mois",R.drawable.fv,R.drawable.fv,R.drawable.fv))
//
// data2.add(ItemsLigne2Model(R.drawable.m5,R.drawable.m5,R.drawable.plce,"cité","chambre 1","200 000/ans","1 chambre en cour de l'iberartion","3 chambre disponible","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
// data2.add(ItemsLigne2Model(R.drawable.m6,R.drawable.m6,R.drawable.plce,"cité","chambre 2","250 000/ans","1 chambre en cour de l'iberartion","3 chambre disponible","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
// data2.add(ItemsLigne2Model(R.drawable.m7,R.drawable.m7,R.drawable.plce,"cité","chambre 3","300 000/ans","1 chambre en cour de l'iberartion","3 chambre disponible","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
//
// data3.add(ItemsLigne3Model(R.drawable.m4,R.drawable.plce,"bonandjo","Maison","Maison","4 chambre 1 salon 2 douche","300 000/ans"))
// data3.add(ItemsLigne3Model(R.drawable.m2,R.drawable.plce,"bonandjo2","Maison","Maison","4 chambre 1 salon 2 douche","300 000/ans"))
// data3.add(ItemsLigne3Model(R.drawable.m1,R.drawable.plce,"bonandjo3","Maison","Maison","4 chambre 1 salon 2 douche","300 000/ans"))
//
// data4.add(ItemsLigne4Model(R.drawable.m7,R.drawable.plce,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
// data4.add(ItemsLigne4Model(R.drawable.m5,R.drawable.plce,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
// data4.add(ItemsLigne4Model(R.drawable.m6,R.drawable.plce,R.drawable.like,"duplex","duplex","400 000/mois","cite blanche","3 chambre disponible","2 chambre en cour de libeation","bastos",R.drawable.fv,R.drawable.fv,R.drawable.fv))
//
// data5.add(ItemsLigne5Model(R.drawable.m3,"cite reine","chambre1","bangante","150 000/ans"))
// data5.add(ItemsLigne5Model(R.drawable.m4,"cite reine","chambre4","bangante","180 000/ans"))
// data5.add(ItemsLigne5Model(R.drawable.m2,"cite reine","chambre6","bangante","100 000/ans"))
// data5.add(ItemsLigne5Model(R.drawable.m1,"cite reine","chambre7","bangante", "200 000/ans"))
//
// data2Search.add(
// ItemsViewModel2(
// R.drawable.m1,
// "cité hypocratea",
// "24 chambre",
// "20 chambre libre",
// "4 en cours de l'iberation",
// "fv  lv  ds",
// "fv  lv"
// )
// )
// data2Search.add(
// ItemsViewModel2(
// R.drawable.m2,
// "cité hypocrateb",
// "24 chambre",
// "20 chambre libre",
// "4 en cours de l'iberation",
// "fv  lv  ds",
// "fv  lv"
// )
// )
// data2Search.add(
// ItemsViewModel2(
// R.drawable.m3,
// "cité hypocrate",
// "24 chambre",
// "20 chambre libre",
// "4 en cours de l'iberation",
// "fv  lv  ds",
// "fv  lv"
// )
// )
//
//
// data3Search.add(
// ItemsViewModel3(
// R.drawable.m4,
// "cité rose",
// "24 chambre",
// "20 chambre libre",
// "4 en cours de l'iberation",
// "fv  lv  ds",
// "fv  lv"
// )
// )
// data3Search.add(
// ItemsViewModel3(
// R.drawable.m5,
// "cité rose",
// "24 chambre",
// "20 chambre libre",
// "4 en cours de l'iberation",
// "fv  lv  ds",
// "fv  lv"
// )
// )
// data3Search.add(
// ItemsViewModel3(
// R.drawable.m6,
// "cité rose",
// "24 chambre",
// "20 chambre libre",
// "4 en cours de l'iberation",
// "fv  lv  ds",
// "fv  lv"
// )
// )
// data3Search.add(
// ItemsViewModel3(
// R.drawable.m7,
// "cité rose",
// "24 chambre",
// "20 chambre libre",
// "4 en cours de l'iberation",
// "fv  lv  ds",
// "fv  lv"
// )
// )
//
//
// data.add(ItemsViewModel(data2Search))
// data.add(ItemsViewModel(data2Search))
// data.add(ItemsViewModel(data2Search))
//
// dataa.add(ItemsViewModell(data3Search))
// dataa.add(ItemsViewModell(data3Search))
// dataa.add(ItemsViewModell(data3Search))
//
// // This will pass the ArrayList to our Adapter
// recyclerview.layoutManager = LinearLayoutManager(this)
//
// //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()
//
//
// val intentSearch: Intent = Intent(this@copieHomeActivity, DetailCiteActivity3::class.java)
//
//
// val adapterSearch = CustomAdapter(data, dataa, this, "detail_cite", intentSearch)
//
//
// recyclerview.adapter = adapterSearch
//
//
// /*  listdataHome.add(HomeModel(dataHome))*/
//
// listdata1.add(ItemsLigne1(data1))
// listdata2.add(ItemsLigne2(data2))
// listdata3.add(ItemsLigne3(data3))
// listdata4.add(ItemsLigne4(data4))
// listdata5.add(ItemsLigne5(data5))
//
// recyclervieww.layoutManager = LinearLayoutManager(this)
//
// val intent: Intent = Intent(this@copieHomeActivity, DetailCiteActivity3::class.java)
//
// val adapter = CustomAdapterHome(dataHome,listdata1, listdata2,listdata3,listdata4,listdata5, this, "detail_cite", intent)
//
//
// recyclervieww.adapter = adapter
// }
//
// interface ClickListener {
// fun onClick(view: View, position: Int)
//
// fun onLongClick(view: View?, position: Int)
// }
//
// internal class RecyclerTouchListener(
// context: Context,
// recyclerView: RecyclerView,
// private val clickListener: ClickListener?
// ) : RecyclerView.OnItemTouchListener {
//
// private val gestureDetector: GestureDetector
//
// init {
// gestureDetector =
// GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
// override fun onSingleTapUp(e: MotionEvent): Boolean {
// return true
// }
//
// override fun onLongPress(e: MotionEvent) {
// val child = recyclerView.findChildViewUnder(e.x, e.y)
// if (child != null && clickListener != null) {
// clickListener.onLongClick(child, recyclerView.getChildPosition(child))
// }
// }
// })
// }
//
// override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//
// val child = rv.findChildViewUnder(e.x, e.y)
// if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
// clickListener.onClick(child, rv.getChildPosition(child))
// }
// return false
// }
//
// override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}
//
// override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//
// }
// }
// }