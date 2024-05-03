package com.example.lokations

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class Adapter_periode_statistique(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private val NUM_ITEMS = 4

    // Retourne le nombre total de pages
    override fun getItemCount(): Int {
        return NUM_ITEMS
    }

    // Crée le fragment à afficher pour cette page
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> fragment_statistique_Journanlier.newInstance("0", "Page # 1")
            1 -> fragment_statistique_Hebdomadaire.newInstance("0", "Page # 2")
            2 -> fragment_statistique_Mensuel.newInstance("0", "Page # 3")
            3 -> fragment_statistique_annuel.newInstance("0", "Page # 4")

            else -> throw IllegalStateException("Invalid position: $position")
        }
    }
}
