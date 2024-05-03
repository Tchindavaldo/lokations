package com.example.lokations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_chambre_info1.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_chambre_info1 : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_chambre_info1, container, false)
       // val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        val frame_layout: ViewPager2 = view.findViewById(R.id.viewPager_infos_chambre)

        val adapterViewPager = adapteur_fragment_slide_inSlide_chambre_info(requireContext())

        frame_layout.adapter=adapterViewPager
/*
        TabLayoutMediator(tabLayout,  frame_layout) { tab, position ->
            if (position == 0) {
               tab.text="chambre"

            } else if (position == 1) {
                tab.text="infos"

            } else if (position == 2) {
                //    vp.setCurrentItem(2,false)
                tab.text="locataire"
            } else if (position == 3) {
                tab.text="contrat"
            }
        }.attach()
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    val posi = tab.position
                    when (posi) {
                        0 -> {
                            //  holder.frame_layout.removeAllViews()
                            //   holder.frame_layout.addView(Items_data_info_chambre[0].view)
                            val tabView = tab.view
                            frame_layout.setCurrentItem(0,false)

                            tabView.setBackgroundResource(R.drawable.round_black_50_30)
                        }
                        1 -> {
                            val tabView = tab.view
                            tabView.setBackgroundResource(R.drawable.round_black_50_30)
                            frame_layout.setCurrentItem(1,false)

                        }
                        2 -> {
                            val tabView = tab.view
                            tabView.setBackgroundResource(R.drawable.round_black_50_30)

                            frame_layout.setCurrentItem(2,false)
                        }
                        3 -> {
                            val tabView = tab.view
                            tabView.setBackgroundResource(R.drawable.round_black_50_30)
                            frame_layout.setCurrentItem(3,false)

                        }
                        // Ajoutez d'autres cas pour chaque onglet supplémentaire
                    }
                    //  fmn.beginTransaction().replace(R.id.viewPager_infos_chambre, Items_data_info_chambre[posi]).commit()

                }

                override fun onTabUnselected(tab: TabLayout.Tab) {
                    val tabView = tab.view
                    tabView.background=null
                }

                override fun onTabReselected(tab: TabLayout.Tab) {
                    // Ne rien faire lorsque l'onglet est à nouveau sélectionné
                }
            })
            tabLayout.setSelectedTabIndicator(null)
*/
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_chambre_info1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_chambre_info1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}