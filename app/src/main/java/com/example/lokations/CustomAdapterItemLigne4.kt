package com.example.lokations
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapterItemLigne4(private val mList: List<ItemsViewModel2>) : RecyclerView.Adapter<CustomAdapterItemLigne4.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon4, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text ; holder.textViewC.text = ItemsViewModel.text2 ;holder.textViewCl.text = ItemsViewModel.text3 ;holder.textViewClb.text = ItemsViewModel.text4 ;holder.textView2l.text = ItemsViewModel.text5 ;holder.textViewqv.text = ItemsViewModel.text6 ;

    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imghome)
        val textView: TextView = itemView.findViewById(R.id.textView)
        val textViewC: TextView = itemView.findViewById(R.id.textViewC)
        val textViewCl: TextView = itemView.findViewById(R.id.textViewCl)
        val textViewClb: TextView = itemView.findViewById(R.id.textViewClb)
        val textView2l: TextView = itemView.findViewById(R.id.textView2l)
        val textViewqv: TextView = itemView.findViewById(R.id.textViewqv)



    }
}


