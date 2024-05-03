package com.example.lokations


data class DataLoadResult(
    val currentData1: itemSHomeModel?,
    val nextData1: itemSHomeModel?,
    val currentData2: ItemsLigne1?,
    val nextData2: ItemsLigne1?,
    val currentData3: ItemsLigne2?,
    val nextData3: ItemsLigne2?,
    val currentData4: ItemsLigne3?,
    val nextData4: ItemsLigne3?,
    val currentData5: ItemsLigne4?,
    val nextData5: ItemsLigne4?,
    val currentData6: ItemsLigne5?,
    val nextData6: ItemsLigne5?,
    val currentData7: ArrayList<dataClass_img_home_slide>?,
    val nextData7: ArrayList<dataClass_img_home_slide>?
)