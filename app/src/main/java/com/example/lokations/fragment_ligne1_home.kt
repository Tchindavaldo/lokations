package com.example.lokations

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_ligne1_home.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_ligne1_home : Fragment() {
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
val view = inflater.inflate(R.layout.fragment_ligne1_home, container, false)

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        customAdapter(viewPager)


        val itemList = ArrayList<dataClass_img_home_slide>()
        itemList.add(dataClass_img_home_slide(R.drawable.m4,"cité niva","1 chambre en cour de l'iberartion, 1 chambre disponible"))
        itemList.add(dataClass_img_home_slide(R.drawable.m7,"cité Rose","3 chambre en cour de l'iberartion, 3 chambre disponible"))
        itemList.add(dataClass_img_home_slide(R.drawable.m88,"cité Des Anges","2 chambre en cour de l'iberartion, 6 chambre disponible"))
        itemList.add(dataClass_img_home_slide(R.drawable.m91,"cité Hypocrate","5 chambre en cour de l'iberartion, 2 chambre disponible"))


        val adapterViewPager = Adapteur_image_home_slide(requireContext(),itemList)
        val adapterViewPager2 = Adapteur_infini(adapterViewPager)
        viewPager.adapter = adapterViewPager2

          startAutoScroll(viewPager,3000,9000,1500)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_ligne1_home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_ligne1_home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

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