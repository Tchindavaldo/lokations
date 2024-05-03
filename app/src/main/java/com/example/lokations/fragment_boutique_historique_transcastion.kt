package com.example.lokations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_boutique_historique_transcastion.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_boutique_historique_transcastion : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var delay1 =300
    var delay2 =650

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
val view = inflater.inflate(
    R.layout.fragment_boutique_historique_transcastion,
    container,
    false
)




        val item2: ConstraintLayout = view.findViewById(R.id.item1_item2_page_boutique)
        val item3: ConstraintLayout = view.findViewById(R.id.item2_item2_page_boutique)

        item2.alpha=0f
        item3.alpha=0f


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
    override fun onResume() {
        super.onResume()
        val fadeInAnimation = AnimationUtils.loadAnimation(context, R.anim.fade_in_bottom_nav)
        fadeInAnimation.duration = 2000

        val fadeInAnimation2 = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        fadeInAnimation2.duration = 2000

        val item2 = view?.findViewById<View>(R.id.item1_item2_page_boutique)
        val item3 = view?.findViewById<View>(R.id.item2_item2_page_boutique)

        // item2?.alpha = 1f
        // item3?.alpha = 1f
        // item2?.startAnimation(fadeInAnimation)

        item2?.postDelayed(
            {item2.alpha = 1f
                item2.startAnimation(fadeInAnimation)}, delay1.toLong()
        )
        item3?.postDelayed(
            {item3.alpha = 1f
                item3.startAnimation(fadeInAnimation2)}, delay2.toLong()
        )

    }

    override fun onPause() {
        super.onPause()
        val item2 = view?.findViewById<View>(R.id.item1_item2_page_boutique)
        val item3 = view?.findViewById<View>(R.id.item2_item2_page_boutique)

        item2?.alpha = 0f
        item3?.alpha = 0f


         delay1 =0
         delay2 =3000
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_boutique_historique_transcastion.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fragment_boutique_historique_transcastion().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}