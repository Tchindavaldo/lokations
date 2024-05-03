package com.example.lokations
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomHomeAdapterItemLigne3(private val mList: List<ItemsLigne3Model>) : RecyclerView.Adapter<CustomHomeAdapterItemLigne3.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemligne31, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
if(ItemsViewModel.image!=null) {
    // sets the image to the imageview from our itemHolder class
    holder.imageItem.setImageResource(ItemsViewModel.image)
}
        // sets the text to the textview from our itemHolder class
        holder.ville.text = ItemsViewModel.lieux ;
        holder.NomCategori.text = ItemsViewModel.categorie ;
        holder.prix.text = ItemsViewModel.prix ;
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageItem: ImageView = itemView.findViewById(R.id.imageItem)

        val NomCategori: TextView = itemView.findViewById(R.id.NomCategori)
        val ville: TextView = itemView.findViewById(R.id.ville)
        val prix: TextView = itemView.findViewById(R.id.prix)




    }
}


