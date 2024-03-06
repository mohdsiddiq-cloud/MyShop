package com.siddiq.android.clothbazaar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.adapters.ItemAdapter
import com.siddiq.android.clothbazaar.databinding.ActivityItemsBinding
import com.siddiq.android.clothbazaar.databinding.ActivityWishlistBinding
import com.siddiq.android.clothbazaar.models.PopularData

class WishlistActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    lateinit var binding: ActivityWishlistBinding
    lateinit var auth:FirebaseAuth
    lateinit var id:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Firebase.firestore
        auth=Firebase.auth
        id= auth.currentUser?.uid.toString()
        initRecyclerView()
        statusBarColor()
    }
    private fun initRecyclerView() {
        var items: ArrayList<PopularData> = ArrayList()
        val collectionReff=db.collection("Users").document(id).collection("WishList")

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
        val window: Window =this@WishlistActivity.getWindow()
        window.statusBarColor= ContextCompat.getColor(this@WishlistActivity,R.color.purple_dark)
    }
}