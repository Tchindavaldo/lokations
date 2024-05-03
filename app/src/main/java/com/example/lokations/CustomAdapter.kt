package com.example.lokations
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.os.Bundle

import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.app.ActivityOptions.EXTRA_USAGE_TIME_REPORT
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayout


class CustomAdapter(private val mList: List<ItemsViewModel>, private val mList2: List<ItemsViewModell>,
                    val context: Context, val detail_cite: String, val intent: Intent,
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {






    // create new views
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view

        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.icon3, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]
        val ItemsViewModel2 = mList2[position]

        // sets the image to the imageview from our itemHolder class

        holder.text1Linge1.text="top qualité"
        holder.text2Linge1.text="tout voir"

        holder.text1Linge2.text="top qualité"
        holder.text2Linge2.text="tout voir"

        val adapter = CustomAdapter2(ItemsViewModel.rv2)
        holder.rv2.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
        holder.rv2.adapter = adapter

        val adapter2 = CustomAdapter3(ItemsViewModel2.rv3)
        holder.rv3.layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false )
        holder.rv3.adapter = adapter2



        holder.rv2.addOnItemTouchListener(MainActivity.RecyclerTouchListener(context,holder.rv2,object :
            MainActivity.ClickListener {

            override fun onClick(view: View, position: Int) {
                //Toast.makeText(context, ItemsViewModel.rv2[position].text, Toast.LENGTH_SHORT).show()

                startActivity(context, intent, Bundle.EMPTY)

            }

            override fun onLongClick(view: View?, position: Int) {

            }
        }))


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }


    // Holds the views for adding it to image and text
    @RequiresApi(Build.VERSION_CODES.M)
    class ViewHolder(ItemView: View): RecyclerView.ViewHolder(ItemView) {

         val rv2 : RecyclerView  = itemView.findViewById(R.id.rv2)
        val rv3 : RecyclerView = itemView.findViewById(R.id.rv3)
        val text1Linge1 : TextView = itemView.findViewById(R.id.text1Linge1)
        val text2Linge1 : TextView = itemView.findViewById(R.id.text2Linge1)

        val text1Linge2 : TextView = itemView.findViewById(R.id.text1Linge2)
        val text2Linge2 : TextView = itemView.findViewById(R.id.text2Linge2)



    }


}
