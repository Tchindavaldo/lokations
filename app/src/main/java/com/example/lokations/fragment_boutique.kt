package com.example.lokations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_boutique.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_boutique : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_boutique, container, false)
        val item1: CardView = view.findViewById(R.id.entete)

        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val vpPager = view.findViewById<ViewPager2>(R.id.page_boutique)


        val adapterViewPager = Adapter_fragment(this)
        vpPager.adapter = adapterViewPager


        TabLayoutMediator(tabLayout, vpPager) { tab, position ->
            if (position == 0) {
                tab.text = "Transaction"
            } else if (position == 1) {
                tab.text = "Statistique"
            } else if (position == 2) {
                tab.text = "Boutique"
            } else if (position == 3) {
                tab.text = "Publicité"
            }
        }.attach()



        item1.alpha=0f


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onResume() {
        super.onResume()
        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in_bottom_nav)
        fadeInAnimation.duration = 2000
        val item = view?.findViewById<View>(R.id.entete)

        item?.alpha = 1f
        item?.startAnimation(fadeInAnimation)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_boutique.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_boutique().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}