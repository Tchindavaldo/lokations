package com.example.lokations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_boutique_statistique.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_boutique_statistique : Fragment() {
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
        // Inflate the layout for this fragmen
         val view =inflater.inflate(R.layout.fragment_boutique_statistique, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val vpPager = view.findViewById<ViewPager2>(R.id.page_periode_statistique_chalbre)


        val adapterViewPager = Adapter_periode_statistique(this)
        vpPager.adapter = adapterViewPager

        TabLayoutMediator(tabLayout, vpPager) { tab, position ->
            if (position == 0) {
                tab.text = "Journanlier"

                val tabView = tab.view
                tabView.setBackgroundResource(R.drawable.round_black_50_30)
            } else if (position == 1) {
                tab.text = "Hebdomadaire"
            } else if (position == 2) {
                tab.text = "Mensuel"
            } else if (position == 3) {
                tab.text = "Annuel"
            }
        }.attach()

        val tabLayoutWrapper = tabLayout.getChildAt(0) as LinearLayout
        tabLayoutWrapper.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
        tabLayoutWrapper.dividerDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.dp_tab_5)

        tabLayout.setSelectedTabIndicator(null)
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabView = tab.view
                tabView.setBackgroundResource(R.drawable.round_black_50_30)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabView = tab.view
                tabView.background=null
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // Ne rien faire lorsque l'onglet est à nouveau sélectionné
            }
        })
        tabLayout.tabGravity=TabLayout.GRAVITY_CENTER
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_boutique_statistique.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_boutique_statistique().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}