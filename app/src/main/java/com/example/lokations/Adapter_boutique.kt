package com.example.lokations

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class Adapter_fragment(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val NUM_ITEMS = 4

    // Retourne le nombre total de pages
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    // Crée le fragment à afficher pour cette page
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragment_boutique_historique_transcastion.newInstance("0", "Page # 1")
            1 -> fragment_boutique_statistique.newInstance("0", "Page # 2")
            2 -> Fragment_boutique_boutique.newInstance("0", "Page # 3")
            3 -> fragment_boutique_pub.newInstance("0", "Page # 4")


            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
