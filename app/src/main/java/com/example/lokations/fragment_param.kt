package com.example.lokations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_param.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_param : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_param, container, false)

        val item1:LinearLayout = view.findViewById(R.id.item1)
        val item2:LinearLayout = view.findViewById(R.id.item2)
        val item3:LinearLayout = view.findViewById(R.id.item3)
        val item4:LinearLayout = view.findViewById(R.id.item4)
        val item5:LinearLayout = view.findViewById(R.id.item5)
        val item6:LinearLayout = view.findViewById(R.id.item6)
        val item7:LinearLayout = view.findViewById(R.id.item7)
        val item8:LinearLayout = view.findViewById(R.id.item8)
        val item9:LinearLayout = view.findViewById(R.id.item9)
        val item10:LinearLayout = view.findViewById(R.id.item10)

        item1.alpha=0f
        item2.alpha=0f
        item3.alpha=0f
        item4.alpha=0f
        item5.alpha=0f
        item6.alpha=0f
        item7.alpha=0f
        item8.alpha=0f
        item9.alpha=0f
        item10.alpha=0f


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in_bottom_nav)
        fadeInAnimation.duration = 2000

        val item1 = view?.findViewById<View>(R.id.item1)
        val item2 = view?.findViewById<View>(R.id.item2)
        val item3 = view?.findViewById<View>(R.id.item3)
        val item4 = view?.findViewById<View>(R.id.item4)
        val item5 = view?.findViewById<View>(R.id.item5)
        val item6 = view?.findViewById<View>(R.id.item6)
        val item7 = view?.findViewById<View>(R.id.item7)
        val item8 = view?.findViewById<View>(R.id.item8)
        val item9 = view?.findViewById<View>(R.id.item9)
        val item10 = view?.findViewById<View>(R.id.item10)

//        element3?.startAnimation(fadeInAnimation)



        val itemList = listOf(item1,item2,item3,item4,item5,item6,item7,item8,item9,item10 )
        val initialDelay = 100
        val delaiBetweenAnimation = 200
        var totalDelay = initialDelay


        for (item in itemList)
        {
            item?.alpha = 1f
            item?.startAnimation(fadeInAnimation)
            }
            /*item?.postDelayed(
                {item.alpha = 1f
                    item.startAnimation(fadeInAnimation)}, totalDelay.toLong()
            )
            totalDelay += delaiBetweenAnimation

        }*/
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_param.
         *
         * override fun onResume() {
        super.onResume()

        // Appliquer une animation de fondu pour afficher les éléments
        val fadeInAnimation = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        fadeInAnimation.duration = 1000

        val element1 = view?.findViewById<View>(R.id.element1)
        val element2 = view?.findViewById<View>(R.id.element2)
        val element3 = view?.findViewById<View>(R.id.element3)

        element1?.startAnimation(fadeInAnimation)
        element2?.startAnimation(fadeInAnimation)
        element3?.startAnimation(fadeInAnimation)
        }
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_param().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}