package com.siddiq.android.clothbazaar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.adapters.ItemAdapter
import com.siddiq.android.clothbazaar.databinding.ActivityItemsBinding

import com.siddiq.android.clothbazaar.models.PopularData

class ItemsActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var binding: ActivityItemsBinding
    lateinit var doc:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db=Firebase.firestore
        doc= intent.getStringExtra("document").toString()
        initRecyclerView()
        statusBarColor()
        binding.ItemHeadLine.text=doc
    }

    private fun initRecyclerView() {
        var items: ArrayList<PopularData> = ArrayList()
        val collectionReff=db.collection("clothingItems").document(doc).collection("Items")

        collectionReff.get().addOnSuccessListener { documents->
            for (data in documents){
                items.add(data.toObject(PopularData::class.java))
            }

            binding.recyclerView.layoutManager=
                GridLayoutManager(this, 2)
            binding.recyclerView.adapter= ItemAdapter(items)

        }.addOnFailureListener {

        }
    }
    private fun statusBarColor() {
        val window: Window =this@ItemsActivity.getWindow()
        window.statusBarColor= ContextCompat.getColor(this@ItemsActivity,R.color.purple_dark)
    }
}