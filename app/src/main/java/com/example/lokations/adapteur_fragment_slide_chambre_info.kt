package com.example.lokations



import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class adapteur_fragment_slide_chambre_info(
    private val  context: Context, val data: Fragment
   ) : FragmentStateAdapter(context as FragmentActivity) {
    private val NUM_ITEMS = 1
// val infos1:Fragment, val infos2:Fragment, val infos3:Fragment, val infos4:Fragment
    // Retourne le nombre total de pages
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    // Crée le fragment à afficher pour cette page
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 ->data

            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
