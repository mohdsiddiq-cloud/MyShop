package com.siddiq.android.clothbazaar.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddiq.android.clothbazaar.R
import com.siddiq.android.clothbazaar.adapters.CartAdapter
import com.siddiq.android.clothbazaar.databinding.ActivityCartBinding
import com.siddiq.android.clothbazaar.helper.ChangeNumberItemsListener
import com.siddiq.android.clothbazaar.helper.ManagementCart


class CartActivity : AppCompatActivity() {
    lateinit var managementCart: ManagementCart
    lateinit var binding:ActivityCartBinding
    var tax=0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managementCart= ManagementCart(this)
        setVariable()
        initlist()
        calculatorCart()
        statusBarColor()
    }

    private fun statusBarColor() {
        val window: Window =this@CartActivity.getWindow()
        window.statusBarColor= ContextCompat.getColor(this@CartActivity,R.color.purple_dark)
    }

    private fun initlist() {
        if(managementCart.getListCart().isEmpty()){
            binding.emptyTxt.visibility= View.VISIBLE
            binding.scroll.visibility= View.GONE
        }
        else{
            binding.emptyTxt.visibility= View.GONE
            binding.scroll.visibility= View.VISIBLE
        }

        binding.CartView.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
binding.CartView.adapter=CartAdapter(managementCart.getListCart(), object : ChangeNumberItemsListener{

    override fun change(){
        calculatorCart()
    }

} )
    }
    private fun calculatorCart(){
        val percentTax: Double=0.02
        val delivery: Double= 10.0
        tax= (Math.round(managementCart.getTotalFee()*percentTax*100)/100).toDouble()
        val total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100)/100
        val itemTotal = Math.round(managementCart.getTotalFee() * 100)/100
        binding.totalFeeTxt.text="$"+itemTotal
        binding.taxTxt.text="$"+tax
        binding.deliveryTxt.text="$"+delivery
        binding.totalTxt.text="$"+total
    }

    private fun setVariable() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }

}