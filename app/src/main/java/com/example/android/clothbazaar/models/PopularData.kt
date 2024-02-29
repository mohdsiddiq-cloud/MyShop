package com.example.android.clothbazaar.models



data class PopularData(var title:String, var picUrl:String, var review:Int, var score:Double,var numberInCart:Int, var price: Double, var description: String)
    : java.io.Serializable