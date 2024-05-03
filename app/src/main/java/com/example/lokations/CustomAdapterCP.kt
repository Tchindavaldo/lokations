package com.example.lokations
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/*
class CustomAdapterCP(private val mList: List<ItemsViewModel>) : RecyclerView.Adapter<CustomAdapterCP.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon3, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)
        holder.imghome2.setImageResource(ItemsViewModel.image2)
        holder.imghome4.setImageResource(ItemsViewModel.image3)
        holder.imghome5.setImageResource(ItemsViewModel.image4)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text ; holder.textViewC.text = ItemsViewModel.text2 ;holder.textViewCl.text = ItemsViewModel.text3 ;holder.textViewClb.text = ItemsViewModel.text4 ;holder.textView2l.text = ItemsViewModel.text5 ;holder.textViewqv.text = ItemsViewModel.text6 ;
        holder.textView44.text = ItemsViewModel.text ;holder.textView2C.text = ItemsViewModel.text2 ;holder.textView2Cl.text = ItemsViewModel.text3 ;holder.textView2Clb.text = ItemsViewModel.text4
        holder.textView44.text = ItemsViewModel.text ;holder.textView3C.text = ItemsViewModel.text2 ;holder.textView3Cl.text = ItemsViewModel.text3 ;holder.textView3Clb.text = ItemsViewModel.text4 ;holder.textView4.text = ItemsViewModel.text5 ;holder.textViewqv3.text = ItemsViewModel.text6 ;
        holder.textView55.text = ItemsViewModel.text ;holder.textView4C.text = ItemsViewModel.text2 ;holder.textView4Cl.text = ItemsViewModel.text3 ;holder.textView4Clb.text = ItemsViewModel.text4

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


        val imghome2: ImageView = itemView.findViewById(R.id.imghome2)
        val textView33: TextView = itemView.findViewById(R.id.textView33)
        val textView2C: TextView = itemView.findViewById(R.id.textView2C)
        val textView2Cl: TextView = itemView.findViewById(R.id.textView2Cl)
        val textView2Clb: TextView = itemView.findViewById(R.id.textView2Clb)

        val imghome4: ImageView = itemView.findViewById(R.id.imghome4)
        val textView44: TextView = itemView.findViewById(R.id.textView44)
        val textView3C: TextView = itemView.findViewById(R.id.textView3C)
        val textView3Cl: TextView = itemView.findViewById(R.id.textView3Cl)
        val textView3Clb: TextView = itemView.findViewById(R.id.textView3Clb)
        val textView4: TextView = itemView.findViewById(R.id.textView4)
        val textViewqv3: TextView = itemView.findViewById(R.id.textViewqv3)


        val imghome5: ImageView = itemView.findViewById(R.id.imghome5)
        val textView55: TextView = itemView.findViewById(R.id.textView55)
        val textView4C: TextView = itemView.findViewById(R.id.textView4C)
        val textView4Cl: TextView = itemView.findViewById(R.id.textView4Cl)
        val textView4Clb: TextView = itemView.findViewById(R.id.textView4Clb)
    }
}
*/