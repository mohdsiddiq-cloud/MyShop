package com.siddiq.android.clothbazaar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.adapters.PopularDataAdapter
import com.siddiq.android.clothbazaar.database.DummyDatabase
import com.siddiq.android.clothbazaar.databinding.ActivityShopBinding
import com.siddiq.android.clothbazaar.models.PopularData

class ShopActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopBinding
    lateinit var db: FirebaseFirestore
    lateinit var user:FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db= Firebase.firestore
        user= Firebase.auth.currentUser!!
        statusBarColor()
        initRecyclerView()
        bottomNavigation()
        setName()
        clickListener()
//        addData()
    }

//    private fun addData() {
//        var database= DummyDatabase()
//        database.popular()
//        database.addShirts()
//        database.addTShirts()
//        database.addJeans()
//        database.addSkirts()
//        database.addFrocks()
//        database.addKurtis()
//        database.addShoes()
//        database.addJackets()
//        database.addSweaters()
//        database.addKids()
//    }

    private fun setName() {
        var docRef=db.collection("Users").document(user.uid)
        docRef.addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            if (value != null && value.exists()) {
                binding.profileName.text=value.getString("name")
            } else {

            }
        }
    }

    private fun clickListener() {
        binding.shirtImage.setOnClickListener {
            itemClick("Shirts")
        }

        binding.tshirtImage.setOnClickListener {
            itemClick("T-Shirts")
        }
        binding.jeansImage.setOnClickListener {
            itemClick("Jeans")
        }
        binding.skirtImage.setOnClickListener {
            itemClick("Skirts")
        }
        binding.frockImage.setOnClickListener {
            itemClick("Frocks")
        }
        binding.kurtisImage.setOnClickListener {
            itemClick("Kurtis")
        }
        binding.shoesImage.setOnClickListener {
            itemClick("Shoes")
        }
        binding.jacketsImage.setOnClickListener {
            itemClick("Jackets")
        }
        binding.sweaterImage.setOnClickListener {
            itemClick("Sweaters")
        }
        binding.kidsImage.setOnClickListener {
            itemClick("Kids")
        }
        binding.seeAll.setOnClickListener {
            itemClick("Popular Products")
        }
    }

    private fun itemClick(item:String){
        val intent=Intent(this,ItemsActivity::class.java);
        intent.putExtra("document",item)
        startActivity(intent)
    }

    private fun bottomNavigation() {
        binding.cartPage.setOnClickListener {
            if (this::class.java.name != CartActivity::class.java.name) {
                val intent = Intent(this, CartActivity::class.java)
                startActivity(intent)
            }
        }

        binding.wishlist.setOnClickListener {
            if (this::class.java.name != WishlistActivity::class.java.name) {
                val intent = Intent(this, WishlistActivity::class.java)
                startActivity(intent)
            }
        }

        binding.profile.setOnClickListener {
            if (this::class.java.name != ProfileActivity::class.java.name) {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
            }
        }

        binding.home.setOnClickListener {
            if (this::class.java.name != ShopActivity::class.java.name) {
                val intent = Intent(this, ShopActivity::class.java)
                startActivity(intent)
            }
        }

        binding.notification.setOnClickListener {
            if (this::class.java.name != NotificationActivity::class.java.name) {
                val intent = Intent(this, NotificationActivity::class.java)
                startActivity(intent)
            }
        }
    }



    private fun statusBarColor() {
        val window:Window=this@ShopActivity.getWindow()
        window.statusBarColor=ContextCompat.getColor(this@ShopActivity,R.color.purple_dark)
    }

    private fun initRecyclerView() {
       var items: ArrayList<PopularData> = ArrayList<PopularData>()
       items.add(PopularData("1","Shirt","popularshirt",15,4.0,1,700.0,"test", emptyList<String>(),
           emptyList<String>()
       ))
        items.add(PopularData("2","T-shirt","populartshirt",10,5.0,3,1500.0,"test", emptyList<String>(),
            emptyList<String>()))
        items.add(PopularData("3","Shoes","popularshoes",13,3.0,2,1250.0,"test", emptyList<String>(),
            emptyList<String>()))

        binding.PopularView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.PopularView.adapter=PopularDataAdapter(items)
    }

}