package com.example.lokations

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_statistique_Mensuel.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_statistique_Mensuel : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_statistique_mensuel, container, false)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val vpPager = view.findViewById<ViewPager2>(R.id.statistique_chambre)


        val adapterViewPager = Adapter_statistique_chambre(this)
        vpPager.adapter = adapterViewPager
        TabLayoutMediator(tabLayout, vpPager) { tab, position ->
            if (position == 0) {
                tab.text = "chambre1".lowercase()
            } else if (position == 1) {
                tab.text = "Chambre2".lowercase()
            } else if (position == 2) {
                tab.text = "Chambre3".toLowerCase()
            } else if (position == 3) {
                tab.text = "Chambre4".toLowerCase()
            } else if (position == 4) {
                tab.text = "Chambre5".toLowerCase()
            } else if (position == 5) {
                tab.text = "Chambre6".toLowerCase()
            } else if (position == 6) {
                tab.text = "Chambre7".toLowerCase()
            }
        }.attach()
        tabLayout.setSelectedTabIndicator(null)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_statistique_Mensuel.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_statistique_Mensuel().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}