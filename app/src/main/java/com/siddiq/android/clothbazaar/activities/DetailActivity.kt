package com.siddiq.android.clothbazaar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.databinding.ActivityDetailBinding
import com.siddiq.android.clothbazaar.helper.ManagementCart
import com.siddiq.android.clothbazaar.models.PopularData

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var data: PopularData
    lateinit var db:FirebaseFirestore
    lateinit var id:String

    lateinit var  reff: CollectionReference
    var numberOrder=1;
    lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db=Firebase.firestore
        id=Firebase.auth.currentUser?.uid.toString()
        data  =  intent.getSerializableExtra("object") as PopularData
        reff= db.collection("Users").document(id).collection("WishList")
        getBundles()
        statusBarColor()

        managementCart= ManagementCart(this)
        binding.wishlistSave.setOnClickListener {
            addWishList()
        }
        binding.wishlistUnSave.setOnClickListener {
            addWishList()
        }
        wishlistCheck()
    }

    private fun wishlistCheck(){
        reff.document(data.id).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    binding.wishlistUnSave.visibility= View.GONE
                    binding.wishlistSave.visibility= View.VISIBLE
                } else {
                    binding.wishlistUnSave.visibility= View.VISIBLE
                    binding.wishlistSave.visibility= View.GONE
                }
            }
            .addOnFailureListener { exception ->
                // Handle error
            }
    }

    private fun addWishList() {

        reff.document(data.id).get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    reff.document(data.id).delete().addOnSuccessListener {
                        binding.wishlistUnSave.visibility= View.VISIBLE
                        binding.wishlistSave.visibility= View.GONE
                        Toast.makeText(this,"Remove from wishlist",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
                    }
                }

                else {
                    reff.document(data.id).set(data).addOnSuccessListener {
                        binding.wishlistUnSave.visibility= View.GONE
                        binding.wishlistSave.visibility= View.VISIBLE
                        Toast.makeText(this,"Added to wishlist",Toast.LENGTH_SHORT).show()
                    }.addOnFailureListener {
                        Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
                    }

                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this,"Something Went Wrong",Toast.LENGTH_SHORT).show()
            }

    }

    private fun statusBarColor() {
        val window: Window =this@DetailActivity.getWindow()
        window.statusBarColor= ContextCompat.getColor(this@DetailActivity,R.color.purple_dark)
    }

    private fun getBundles() {

        val drawableResourceId= this.resources.getIdentifier(data.picUrl,"mipmap",this.packageName)
        Glide.with(this).load(drawableResourceId).into(binding.itemPic)

        binding.titileText.text=data.title
        binding.priceText.text="₹"+data.price.toString()
        binding.descriptionText.text=data.description
//        binding.reviewText.text=data.review.toString()
//        binding.ratingText.text=data.score.toString()

        binding.addToCartBtn.setOnClickListener {
            data.numberInCart=numberOrder
            managementCart.insertFood(data)
        }
        binding.backButton.setOnClickListener {
            finish()
        }

    }
}