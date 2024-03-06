package com.siddiq.android.clothbazaar.models

data class PersonalData(val name:String,val email:String,val address:String,val uid:String,var wishlist:List<PopularData>)