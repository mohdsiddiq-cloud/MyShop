package com.siddiq.android.clothbazaar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.databinding.ActivityDetailBinding
import com.siddiq.android.clothbazaar.helper.ManagementCart
import com.siddiq.android.clothbazaar.models.PopularData

class DetailActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailBinding
    lateinit var data: PopularData
    var numberOrder=1;
    lateinit var managementCart: ManagementCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundles()
        statusBarColor()
        managementCart= ManagementCart(this)
    }

    private fun statusBarColor() {
        val window: Window =this@DetailActivity.getWindow()
        window.statusBarColor= ContextCompat.getColor(this@DetailActivity,R.color.purple_dark)
    }

    private fun getBundles() {
        data  =  intent.getSerializableExtra("object") as PopularData

        val drawableResourceId= this.resources.getIdentifier(data.picUrl,"drawable",this.packageName)
        Glide.with(this).load(drawableResourceId).into(binding.itemPic)

        binding.titileText.text=data.title
        binding.priceText.text="$"+data.price.toString()
        binding.descriptionText.text=data.description
        binding.reviewText.text=data.review.toString()
        binding.ratingText.text=data.score.toString()

        binding.addToCartBtn.setOnClickListener {
            data.numberInCart=numberOrder
            managementCart.insertFood(data)
        }
        binding.backButton.setOnClickListener {
            finish()
        }

    }
}