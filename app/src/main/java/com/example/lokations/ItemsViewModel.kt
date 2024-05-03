package com.example.lokations

import androidx.recyclerview.widget.RecyclerView

data class ItemsViewModel(val rv2 : ArrayList<ItemsViewModel2> ) {
}

data class ItemsViewModell(val rv3 : ArrayList<ItemsViewModel3> ) {
}

data class ItemsViewModel2(val image: Int, val text: String,val text2: String,val text3: String,val text4: String,val text5: String,val text6: String) {
}

data class ItemsViewModel3(val image: Int, val text: String,val text2: String,val text3: String,val text4: String,val text5: String,val text6: String) {
}