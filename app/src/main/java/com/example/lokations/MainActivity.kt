package com.example.lokations

import android.annotation.SuppressLint
import android.content.ClipData.Item
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(){


    lateinit var tabLayout: TabLayout
    // lateinit var viewPager: ViewPager
    lateinit var intent2: Intent

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolb: androidx.appcompat.widget.Toolbar = findViewById(R.id.to)
        setSupportActionBar(toolb)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        //   viewPager = findViewById<ViewPager>(R.id.viewPager)

        tabLayout.addTab(tabLayout.newTab().setText("prix"))
        tabLayout.addTab(tabLayout.newTab().setText("klity"))
        tabLayout.addTab(tabLayout.newTab().setText("comment"))
        tabLayout.addTab(tabLayout.newTab().setText("ville"))
        tabLayout.addTab(tabLayout.newTab().setText("position"))
        tabLayout.addTab(tabLayout.newTab().setText("like"))
        tabLayout.addTab(tabLayout.newTab().setText("visite"))
        tabLayout.addTab(tabLayout.newTab().setText("prix"))
        tabLayout.addTab(tabLayout.newTab().setText("klity"))
        tabLayout.addTab(tabLayout.newTab().setText("comment"))



        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)

        val data = ArrayList<ItemsViewModel>()
        val dataa = ArrayList<ItemsViewModell>()
        val data2 = ArrayList<ItemsViewModel2>()
        val data3 = ArrayList<ItemsViewModel3>()


        // val recyclerview2 = findViewById<RecyclerView>(R.id.rv2)
        //val recyclerview3 = findViewById<RecyclerView>(R.id.rv3)

        // this creates a vertical layout Manager

        //recyclerview2.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false )
        //recyclerview3.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false )

        // ArrayList of class ItemsViewModel

        // This loop will create 20 Views containing
        // the image with the count of view

      // intent2 =  Intent (this@MainActivity, HomeActivity::class.java) ;

       /* val item = null
        NavigationBarView.OnItemSelectedListener { item ->
            when (item.itemId) {



                R.id.page_2 -> { startActivity(intent2)
                    true
                }

                R.id.page_3 -> { startActivity(intent2)
                    true
                }

home                else -> false
            } }
*/
            data2.add(
                ItemsViewModel2(
                    R.drawable.m1,
                    "cité hypocratea",
                    "24 chambre",
                    "20 chambre libre",
                    "4 en cours de l'iberation",
                    "fv  lv  ds",
                    "fv  lv"
                )
            )
            data2.add(
                ItemsViewModel2(
                    R.drawable.m2,
                    "cité hypocrateb",
                    "24 chambre",
                    "20 chambre libre",
                    "4 en cours de l'iberation",
                    "fv  lv  ds",
                    "fv  lv"
                )
            )
            data2.add(
                ItemsViewModel2(
                    R.drawable.m3,
                    "cité hypocrate",
                    "24 chambre",
                    "20 chambre libre",
                    "4 en cours de l'iberation",
                    "fv  lv  ds",
                    "fv  lv"
                )
            )


            data3.add(
                ItemsViewModel3(
                    R.drawable.m4,
                    "cité rose",
                    "24 chambre",
                    "20 chambre libre",
                    "4 en cours de l'iberation",
                    "fv  lv  ds",
                    "fv  lv"
                )
            )
            data3.add(
                ItemsViewModel3(
                    R.drawable.m5,
                    "cité rose",
                    "24 chambre",
                    "20 chambre libre",
                    "4 en cours de l'iberation",
                    "fv  lv  ds",
                    "fv  lv"
                )
            )
            data3.add(
                ItemsViewModel3(
                    R.drawable.m6,
                    "cité rose",
                    "24 chambre",
                    "20 chambre libre",
                    "4 en cours de l'iberation",
                    "fv  lv  ds",
                    "fv  lv"
                )
            )
            data3.add(
                ItemsViewModel3(
                    R.drawable.m7,
                    "cité rose",
                    "24 chambre",
                    "20 chambre libre",
                    "4 en cours de l'iberation",
                    "fv  lv  ds",
                    "fv  lv"
                )
            )


            data.add(ItemsViewModel(data2))
            data.add(ItemsViewModel(data2))
            data.add(ItemsViewModel(data2))

            dataa.add(ItemsViewModell(data3))
            dataa.add(ItemsViewModell(data3))
            dataa.add(ItemsViewModell(data3))

            // This will pass the ArrayList to our Adapter
            recyclerview.layoutManager = LinearLayoutManager(this)

            //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()


            val intent: Intent = Intent(this@MainActivity, DetailCiteActivity3::class.java)


            val adapter = CustomAdapter(data, dataa, this, "detail_cite", intent)


            recyclerview.adapter = adapter


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


        //val adapter2 = Custdapter(dataa, this)

        // Setting the Adapter with the recyclerview





     //   var unsel = ContextCompat.getColor(this, R.color.teal_200)
      //  var sel = ContextCompat.getColor(this, R.color.cust)
/*var i : Int
        tabLayout.getTabAt(0).getIcon().setColorFilter(sel, PorterDuff.Mode.SRC_IN)
        for (  i  = 1; i < tabLayout.getTabCount(); i++)
        {
        tabLayout.getTabAt(0).getIcon().setColorFilter(sel, PorterDuff.Mode.SRC_IN) }
*/
/*
        val adapter = MyAdapter(this, supportFragmentManager, tabLayout!!.tabCount)
        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {

            }
            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        }) */
/*



        // Tabs Customization
        tab_layout.setSelectedTabIndicatorColor(Color.WHITE)
        tab_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimaryDark))
        tab_layout.tabTextColors = ContextCompat.getColorStateList(this, android.R.color.white)

        // Set different Text Color for Tabs for when are selected or not
        //tab_layout.setTabTextColors(R.color.normalTabTextColor, R.color.selectedTabTextColor)

        // Number Of Tabs
        val numberOfTabs = 3

        // Set Tabs in the center
        //tab_layout.tabGravity = TabLayout.GRAVITY_CENTER

        // Show all Tabs in screen
        tab_layout.tabMode = TabLayout.MODE_FIXED

        // Scroll to see all Tabs
        //tab_layout.tabMode = TabLayout.MODE_SCROLLABLE

        // Set Tab icons next to the text, instead above the text
        tab_layout.isInlineLabel = true


        // ...


        // Set the ViewPager Adapter
        val adapter = TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        tabs_viewpager.adapter = adapter

        // Enable Swipe
        tabs_viewpager.isUserInputEnabled = true

        TabLayoutMediator(tab_layout, tabs_viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Music"
                    tab.setIcon(R.drawable.ic_music)
                }
                1 -> {
                    tab.text = "Movies"
                    tab.setIcon(R.drawable.ic_movie)

                }
                2 -> {
                    tab.text = "Books"
                    tab.setIcon(R.drawable.ic_book)
                }

            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()
*/



}
