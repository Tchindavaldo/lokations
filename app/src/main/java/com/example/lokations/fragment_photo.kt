package com.example.lokations

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.ViewPager
import me.relex.circleindicator.CircleIndicator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [fragment_photo.newInstance] factory method to
 * create an instance of this fragment.
 */
class fragment_photo : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var param3: Bitmap? = null

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
        var selectfrg  : Fragment? = null
        var typeTransac: String? = null
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_photo, container, false)

        val textViewcatg = view.findViewById<TextView>(R.id.categorie)
        val textViewEmplacement = view.findViewById<ConstraintLayout>(R.id.emplacement)
        val textViewLieux = view.findViewById<TextView>(R.id.lieux)
        val textViewItemCatg = view.findViewById<TextView>(R.id.Itemcategorie)
        val textViewprix: TextView = view.findViewById(R.id.prix)
        val textViewlabel_Itemcategorie: TextView = view.findViewById(R.id.label_Itemcategorie)


        val top_detail : LinearLayout = view.findViewById(R.id.top_detail)
        val bottom_detail : LinearLayout = view.findViewById(R.id.bottom_detail)


        val catg = arguments?.getString(ARG_PARAM1)
        val itemCatg = arguments?.getString(ARG_PARAM2)
        val lieux = arguments?.getString(ARG_PARAM3)
        val prix = arguments?.getString(ARG_PARAM4)
        val image = arguments?.getParcelable<Bitmap>(ARG_PARAM5)


      //  val image2 = BitmapFactory.decodeResource(resources, image)
       // val view_image: ImageView= view.findViewById(R.id.imgchambre)


        //view_image.setImageBitmap(image)
        textViewcatg.text=catg
        textViewItemCatg.text=itemCatg
        textViewLieux.text=lieux
        textViewprix.text=prix

       // top_detail.translationX = -top_detail.width.toFloat() // Déplace le layout hors de l'écran à gauche
      //  bottom_detail.translationX = -bottom_detail.width.toFloat() // Déplace le layout hors de l'écran à gauche
        textViewcatg.translationY = -74f // Déplace le layout hors de l'écran à gauche
        textViewEmplacement.translationX = -355f // Déplace le layout hors de l'écran à gauche

        textViewItemCatg.translationX = -225f // Déplace le layout hors de l'écran à gauche
        textViewprix.translationX = -245f // Déplace le layout hors de l'écran à gauche
        textViewlabel_Itemcategorie.translationY = 85f // Déplace le layout hors de l'écran à gauche


        val itemList = ArrayList<ViewItem>()
        itemList.add(ViewItem(R.drawable.login_img1))
        itemList.add(ViewItem(R.drawable.login_img2))
        itemList.add(ViewItem(R.drawable.login_img3))
        itemList.add(ViewItem(R.drawable.login_img4))
        itemList.add(ViewItem(R.drawable.login_img5))
        itemList.add(ViewItem(R.drawable.login_img6))

        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        viewPager.setClipToPadding(false)
        viewPager.clipChildren=false
        viewPager.offscreenPageLimit=4




        //  viewPager.setPadding(200, 0, 200, 0)
        viewPager.setPageMargin(20)
        viewPager.setPageTransformer(true, object : ViewPager.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val scaleFactor = 0.35f
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
        val adapter = adapteur_image_fragment_photo(requireContext(), itemList)
        viewPager.adapter = adapter

        val indicator = view.findViewById<CircleIndicator>(R.id.indicator)
        indicator.setViewPager(viewPager)

        return view
    }

    override fun onResume() {
        super.onResume()




        val textViewcatg = view?.findViewById<TextView>(R.id.categorie)
        val textViewLieux = view?.findViewById<TextView>(R.id.lieux)
        val textViewEmplacement = view?.findViewById<ConstraintLayout>(R.id.emplacement)
        val textViewItemCatg = view?.findViewById<TextView>(R.id.Itemcategorie)
        val textViewprix = view?.findViewById<TextView>(R.id.prix)

        val textViewlabel_Itemcategorie = view?.findViewById<TextView>(R.id.label_Itemcategorie)
        val container_item_nav   = view?.findViewById<LinearLayout>(R.id.container_item_nav)

        textViewcatg?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 400L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationY = 0f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationY(translationY)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }
        textViewEmplacement?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 500L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationX = 0f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationX(translationX)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }




        textViewItemCatg?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 500L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationX = 0f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationX(translationX)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }
        textViewprix?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 600L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationX = 0f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationX(translationX)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }
        textViewlabel_Itemcategorie?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 400L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationY = 0f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationY(translationY)
                    .setDuration(animationDuration)
                    .start()
            }, delay)
        }







        container_item_nav?.let { layout ->
            val animationDuration = 800L // Durée de l'animation (en millisecondes)
            val delay = 400L // Délai avant le démarrage de l'animation (en millisecondes)

            Handler().postDelayed({
                val translationX = -2f // Position d'origine (pas de décalage horizontal)

                layout.animate()
                    .translationX(translationX)
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
         * @return A new instance of fragment fragment_photo.
         */
        private const val ARG_PARAM1="categorie"
        private const val ARG_PARAM2="itemCategori"
        private const val ARG_PARAM3="lieux"
        private const val ARG_PARAM4="prix"
        private const val ARG_PARAM5="image"
        // TODO: Rename and change types and number of parameters
        @JvmStatic

        fun newInstance(categorie: String,itemCategori:String, lieux: String,prix: String, image: Bitmap) =
            fragment_photo().apply {
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