package com.example.lokations

data class HomeModel(val itemHome : ArrayList<itemSHomeModel> ) {}


data class ItemsLigne1(val itemLigne1 : ArrayList<ItemsLigne1Model> ) {}
data class ItemsLigne2(val itemLigne2 : ArrayList<ItemsLigne2Model> ) { }
data class ItemsLigne3(val itemLigne3 : ArrayList<ItemsLigne3Model> ) { }
data class ItemsLigne4(val itemLigne4 : ArrayList<ItemsLigne4Model> ) { }
data class ItemsLigne5(val itemLigne5 : ArrayList<ItemsLigne5Model> ) { }

data class itemSHomeModel(
    val image: Int,
    val toutVoir: String, val topQualite: String,
    val mieuNote: String,
    val recementConstruit: String,
    val populaire: String,
    val image5: Int,
    val image52: Int,
    val image53: Int,
    val image54: Int,
    val image55: Int,
    val text51: String,
    val text52: String,
    val text53: String,
    val text54: String,
    val text55: String,
    val text56: String,) { }

data class ItemsLigne1Model(val image: Int,val categorie: String,val itemCategorie: String, val lieux: String,val prix: String,val imagefav1: Int, val imagefav2: Int, val imagefav3: Int) { }

data class ItemsLigne2Model(val image: Int?,
                            val image2: Int?,
                            val image3: Int?,
                            val categorie: String?,
                            val itemCategorie: String?,
                            val prix: String?,
                            val text: String?,
                            val text2: String?,
                            val lieux: String?,
                            val imagefav1: Int?,
                            val imagefav2: Int?,
                            val imagefav3: Int?) { }

data class ItemsLigne3Model(val image: Int?,
                            val image2: Int?,
                            val lieux: String?,
                            val categorie: String?,
                            val itemCategorie: String?,
                            val text3: String?,
                            val prix: String?) { }

data class ItemsLigne4Model(val image: Int, val image2: Int, val image3: Int,val categorie: String,val itemCategorie: String,val prix: String,val text: String,val text2: String, val text3: String,val lieux: String,val imagefav1: Int, val imagefav2: Int, val imagefav3: Int) { }

data class ItemsLigne5Model(val image: Int,val categorie: String,val itemCategorie: String,val lieux: String, val prix: String) { }