package com.example.lokations

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

class Adapteur_scroll_infini_fragment(private val context: Context, private val adapter: FragmentStateAdapter) :
    FragmentStateAdapter(context as FragmentActivity) {

    override fun getItemCount(): Int {
        // Utilisez un nombre très élevé pour simuler le défilement infini
        return Int.MAX_VALUE
    }

    override fun createFragment(position: Int): Fragment {
        // Calculer la position réelle dans l'adaptateur d'origine
        val actualPosition = position % adapter.itemCount
        return adapter.createFragment(actualPosition)
    }

    override fun getItemId(position: Int): Long {
        // Utilisez l'ID de l'adaptateur d'origine pour obtenir un ID unique pour chaque position
        val actualPosition = position % adapter.itemCount
        return adapter.getItemId(actualPosition)
    }

    override fun containsItem(itemId: Long): Boolean {
        // Vérifie si l'adaptateur d'origine contient l'élément avec l'ID donné
        return adapter.containsItem(itemId)
    }

    override fun getItemViewType(position: Int): Int {
        // Utilisez le type d'élément de l'adaptateur d'origine pour chaque position
        val actualPosition = position % adapter.itemCount
        return adapter.getItemViewType(actualPosition)
    }

    // Ajoutez d'autres méthodes nécessaires en fonction de vos besoins

}






