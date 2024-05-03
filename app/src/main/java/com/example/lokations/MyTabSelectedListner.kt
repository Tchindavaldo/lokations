package com.example.lokations

import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout

class MyTabSelectedListner(private val fmanager: FragmentManager, private val contaierId:Int): TabLayout.OnTabSelectedListener {


    override fun onTabSelected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")

        val position = tab?.position
        val fragmnt = when (position){

            0-> zina1Fragment()
            1-> zina2Fragment()
            3-> zina3Fragment()
            else -> null
        }

        fragmnt?.let{
            fmanager.beginTransaction().replace(contaierId, it).commit()
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        TODO("Not yet implemented")
    }
}