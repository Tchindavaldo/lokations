package com.example.lokations

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_detail.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_detail : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val textViewItemCatg = view.findViewById<TextView>(R.id.Itemcategorie)
        val textViewprix: TextView = view.findViewById(R.id.prix)
       // val view_image: ImageView = view.findViewById(R.id.imgchambre)
        val container: View = view.findViewById(R.id.cardimgchambre)




        val catg = arguments?.getString(ARG_PARAM1)
        val itemCatg = arguments?.getString(ARG_PARAM2)
        val prix = arguments?.getString(ARG_PARAM4)
        val image = arguments?.getParcelable<Bitmap>(ARG_PARAM5)

      //  view_image.setImageBitmap(image)
        textViewItemCatg.text=itemCatg
        textViewprix.text=prix

        container.alpha=0f

        return view
    }
    override fun onResume() {
        super.onResume()




        val container: View? = view?.findViewById(R.id.cardimgchambre)

        container?.let { layout ->
            val animationDuration = 1000L // Durée de l'animation (en millisecondes)
            val delay = 0L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val alpha = 1f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .alpha(alpha)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment fragment_detail.
         */
        private const val ARG_PARAM1="categorie"
        private const val ARG_PARAM2="itemCategori"
        private const val ARG_PARAM3="lieux"
        private const val ARG_PARAM4="prix"
        private const val ARG_PARAM5="image"
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(categorie: String,itemCategori:String, lieux: String,prix: String, image: Bitmap) =
            fragment_detail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, categorie)
                    putString(ARG_PARAM2, itemCategori)
                    putString(ARG_PARAM3, lieux)
                    putString(ARG_PARAM4, prix)
                    putParcelable(ARG_PARAM5, image)
                }
            }
    }
}