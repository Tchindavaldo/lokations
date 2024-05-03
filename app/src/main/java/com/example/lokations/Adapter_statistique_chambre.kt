package com.example.lokations

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class Adapter_statistique_chambre(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val NUM_ITEMS = 7

    // Retourne le nombre total de pages
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    // Crée le fragment à afficher pour cette page
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> Fragment_statistique_chambre.newInstance("0", "Page # 1")
            1 -> Fragment_statistique_chambre.newInstance("0", "Page # 2")
            2 -> Fragment_statistique_chambre.newInstance("0", "Page # 3")
            3 -> Fragment_statistique_chambre.newInstance("0", "Page # 4")
            4 -> Fragment_statistique_chambre.newInstance("0", "Page # 4")
            5 -> Fragment_statistique_chambre.newInstance("0", "Page # 4")
            6 -> Fragment_statistique_chambre.newInstance("0", "Page # 4")

            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
