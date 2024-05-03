package com.example.lokations

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

class adapteur_recycleView_statistique_chambre( private val data_statistique :ArrayList<data_periodique_statistique_chambre>,val context: Context) : RecyclerView.Adapter<adapteur_recycleView_statistique_chambre.ViewHolder>() {





    // create new views
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.inflate_statistique_chambre, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val Items_data_statistique = data_statistique[position]








        holder.periode.text = Items_data_statistique.periode
        holder.date.text = Items_data_statistique.date

        holder.visite_sur_app.text = Items_data_statistique.visite_sur_app.toString()
        holder.evolution_visite_sur_app.text = Items_data_statistique.evolu_visite_sur_app.toString()

        holder.visite_sur_applm.text = Items_data_statistique.visite_sur_applm.toString()
        holder.evolution_visite_sur_applm.text = Items_data_statistique.evolu_visite_sur_applm.toString()

        holder.nbr_comentaire_positif.text = Items_data_statistique.com_posi.toString()
        holder.evolution_nbr_comentaire_positif.text = Items_data_statistique.evolu_com_posi.toString()

        holder.label_com_neg.text = Items_data_statistique.com_neg.toString()
        holder.label_com_neg_plus.text = Items_data_statistique.evolu_com_neg.toString()

        holder.label_classement_recherche.text = Items_data_statistique.classement_rech.toString()
        holder.label_classement_recherche_evoultion.text = Items_data_statistique.evolu_classement_rech.toString()

        holder.label_classement_app.text = Items_data_statistique.classement_app.toString()
        holder.label_classement_app_plus.text = Items_data_statistique.evolu_classement_app.toString()

        holder.nbr_like_positif.text = Items_data_statistique.like_posi.toString()
        holder.nbr_like_neg.text = Items_data_statistique.like_neg.toString()

        holder.note.text = Items_data_statistique.note.toString()
        holder.nbr_vote.text = Items_data_statistique.nbr_vote.toString()








    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return data_statistique.size
    }


    // Holds the views for adding it to image and text
    @RequiresApi(Build.VERSION_CODES.M)
    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {



        val periode: TextView = itemView.findViewById(R.id.jour)
        val date: TextView = itemView.findViewById(R.id.date)

        val visite_sur_app: TextView = itemView.findViewById(R.id.visite_sur_app)
        val evolution_visite_sur_app: TextView = itemView.findViewById(R.id.evolution_visite_sur_app)

        val visite_sur_applm: TextView = itemView.findViewById(R.id.visite_sur_applm)
        val evolution_visite_sur_applm: TextView = itemView.findViewById(R.id.evolution_visite_sur_applm)

        val nbr_comentaire_positif: TextView = itemView.findViewById(R.id.nbr_comentaire_positif)
        val evolution_nbr_comentaire_positif: TextView = itemView.findViewById(R.id.evolution_nbr_comentaire_positif)

        val label_com_neg: TextView = itemView.findViewById(R.id.label_com_neg)
        val label_com_neg_plus: TextView = itemView.findViewById(R.id.label_com_neg_plus)

        val label_classement_recherche: TextView = itemView.findViewById(R.id.label_classement_recherche)
        val label_classement_recherche_evoultion: TextView = itemView.findViewById(R.id.label_classement_recherche_evoultion)

        val label_classement_app: TextView = itemView.findViewById(R.id.label_classement_app)
        val label_classement_app_plus: TextView = itemView.findViewById(R.id.label_classement_app_plus)

        val nbr_like_positif: TextView = itemView.findViewById(R.id.nbr_like_positif)
        val nbr_like_neg: TextView = itemView.findViewById(R.id.nbr_like_neg)

        val note: TextView = itemView.findViewById(R.id.note)
        val nbr_vote: TextView = itemView.findViewById(R.id.nbr_vote)







    }


}
