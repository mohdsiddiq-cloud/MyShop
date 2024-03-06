package com.siddiq.android.clothbazaar.database

import com.google.firebase.firestore.FirebaseFirestore
import com.siddiq.android.clothbazaar.models.PopularData

class DummyDatabase {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addItems(collectionName: String, itemName: String, price: Double, description: String, sizes: List<String>, colors: List<String>, count: Int = 4) {
        val collectionRef = db.collection("clothingItems").document(collectionName).collection("Items")

        for (i in 1..count) {
            val itemDocRef = collectionRef.document()
            val itemId = itemDocRef.id
            val item = PopularData(itemId, itemName, "", 0, 0.0, 0, price, description, sizes, colors)
            itemDocRef.set(item)
        }
    }

    fun addShirts() {
        addItems("Shirts", "Shirt", 1500.0, "black color shirt", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addTShirts() {
        addItems("T-Shirts", "T-Shirt", 700.0, "black color T-shirt", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addJeans() {
        addItems("Jeans", "Jeans", 2500.0, "black color Jeans", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addSkirts() {
        addItems("Skirts", "Skirts", 900.0, "black color skirts", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addFrocks() {
        addItems("Frocks", "Frocks", 500.0, "black color frocks", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addKurtis() {
        addItems("Kurtis", "Kurtis", 800.0, "black color kurtis", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addShoes() {
        addItems("Shoes", "Shoes", 1700.0, "black color shoes", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addJackets() {
        addItems("Jackets", "Jackets", 1500.0, "black color Jackets", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addSweaters() {
        addItems("Sweaters", "Sweaters", 1500.0, "black color sweaters", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }

    fun addKids() {
        addItems("Kids", "Kids", 1500.0, "black color kids", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }
    fun popular() {
        addItems("Popular Products", "Shirts", 1500.0, "black color shirts", listOf("S", "L", "XL", "XXL"), listOf("black", "white", "red"))
    }
}
