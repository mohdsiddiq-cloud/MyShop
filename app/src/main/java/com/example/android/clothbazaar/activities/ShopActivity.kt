package com.example.android.clothbazaar.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.clothbazaar.R
import com.example.android.clothbazaar.adapters.PopularDataAdapter
import com.example.android.clothbazaar.databinding.ActivityShopBinding
import com.example.android.clothbazaar.models.PopularData

class ShopActivity : AppCompatActivity() {
    lateinit var binding: ActivityShopBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        statusBarColor()
        initRecyclerView()
        bottomNavigation()
    }

    private fun bottomNavigation() {
        binding.cartPage.setOnClickListener {
            val intent= Intent(this,CartActivity::class.java)
            startActivity(intent)
        }

    }

    private fun statusBarColor() {
        val window:Window=this@ShopActivity.getWindow()
        window.statusBarColor=ContextCompat.getColor(this@ShopActivity,R.color.purple_dark)
    }

    private fun initRecyclerView() {
       var items: ArrayList<PopularData> = ArrayList<PopularData>()
       items.add(PopularData("T-shirt Black","item_1",15,4.0,1,500.0,"test"))
        items.add(PopularData("Smart Watch","item_2",13,3.0,2,250.0,"test"))
        items.add(PopularData("Phone","item_3",10,5.0,3,1500.0,"test"))
        binding.PopularView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        binding.PopularView.adapter=PopularDataAdapter(items)
    }

}