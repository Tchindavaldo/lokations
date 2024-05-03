package com.example.lokations
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter3(private val mList: List<ItemsViewModel3>) : RecyclerView.Adapter<CustomAdapter3.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon5, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class

        holder.imghome4.setImageResource(ItemsViewModel.image)


        // sets the text to the textview from our itemHolder class

        holder.textView44.text = ItemsViewModel.text ;holder.textView3C.text = ItemsViewModel.text2 ;holder.textView3Cl.text = ItemsViewModel.text3 ;holder.textView3Clb.text = ItemsViewModel.text4 ;holder.textView4.text = ItemsViewModel.text5 ;holder.textViewqv3.text = ItemsViewModel.text6 ;


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {

        val imghome4: ImageView = itemView.findViewById(R.id.imghome4)
        val textView44: TextView = itemView.findViewById(R.id.textView44)
        val textView3C: TextView = itemView.findViewById(R.id.textView3C)
        val textView3Cl: TextView = itemView.findViewById(R.id.textView3Cl)
        val textView3Clb: TextView = itemView.findViewById(R.id.textView3Clb)
        val textView4: TextView = itemView.findViewById(R.id.textView4)
        val textViewqv3: TextView = itemView.findViewById(R.id.textViewqv3)

    }
}
