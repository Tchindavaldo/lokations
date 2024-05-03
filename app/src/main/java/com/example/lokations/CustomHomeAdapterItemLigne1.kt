package com.example.lokations
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomHomeAdapterItemLigne1(private val mList: List<ItemsLigne1Model>) : RecyclerView.Adapter<CustomHomeAdapterItemLigne1.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.itemligne1, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView1.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView1.text = ItemsViewModel.lieux ;
        holder.textView2.text = ItemsViewModel.prix ;

        holder.imageView3.setImageResource(ItemsViewModel.imagefav1)
        holder.imageView4.setImageResource(ItemsViewModel.imagefav2)
        holder.imageView5.setImageResource(ItemsViewModel.imagefav2)

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView1: ImageView = itemView.findViewById(R.id.ImageItemLigne1)

        val textView1: TextView = itemView.findViewById(R.id.text1item2Ligne1)
        val textView2: TextView = itemView.findViewById(R.id.text2item2Ligne1)

        val imageView3: ImageView = itemView.findViewById(R.id.favItem2lign1)
        val imageView4: ImageView = itemView.findViewById(R.id.fav2Item2lign1)
        val imageView5: ImageView = itemView.findViewById(R.id.fav3Item2lign1)



    }
}


