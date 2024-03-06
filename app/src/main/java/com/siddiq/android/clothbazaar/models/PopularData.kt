package com.siddiq.android.clothbazaar.models



data class PopularData(var id:String, var title:String, var picUrl:String, var review:Int, var score:Double,var numberInCart:Int, var price: Double, var description: String, var size: List<String>,var color:List<String>)
    : java.io.Serializable{
    constructor() : this("","", "", 0, 0.0, 0, 0.0, "", listOf(), listOf())
}