package com.example.lokations



import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class adapteur_fragment_slide_inSlide_chambre_info(
    private val  context: Context,
   ) : FragmentStateAdapter(context as FragmentActivity) {
    private val NUM_ITEMS = 4
// val infos1:Fragment, val infos2:Fragment, val infos3:Fragment, val infos4:Fragment
    // Retourne le nombre total de pages
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    // Crée le fragment à afficher pour cette page
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragment_chambre_info3()
            1 -> fragment_chambre_info3()
            2 -> fragment_chambre_info3()
            3 ->fragment_chambre_info4()

            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
