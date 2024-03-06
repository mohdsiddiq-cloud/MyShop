package com.siddiq.android.clothbazaar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.siddiq.android.clothbazaar.databinding.CartlistItemBinding
import com.siddiq.android.clothbazaar.helper.ChangeNumberItemsListener
import com.siddiq.android.clothbazaar.helper.ManagementCart
import com.siddiq.android.clothbazaar.models.PopularData


class CartAdapter(val items: List<PopularData>,val changeNumberItemsListener: ChangeNumberItemsListener) : Adapter<CartAdapter.MyViewHolder>() {
    lateinit var context: Context
    lateinit var binding : CartlistItemBinding
    lateinit var managementCart:ManagementCart

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context=parent.context
        var inflate=LayoutInflater.from(context)
        binding=CartlistItemBinding.inflate(inflate,parent,false)
        managementCart= ManagementCart(context)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.titleTxt.text=items.get(position).title
        binding.feeEachItem.text="₹"+items.get(position).price.toString()
        binding.totalEachItem.text="₹"+ Math.round(items.get(position).numberInCart*items.get(position).price)
        binding.numberCartItem.text=items.get(position).numberInCart.toString()
        val context=holder.itemView.context

        val drawableResourced=holder.itemView.resources
            .getIdentifier(items.get(position).picUrl,"mipmap",holder.itemView.context.packageName)

        Glide.with(context).load(drawableResourced).transform(GranularRoundedCorners(30F,30F,0F,0F)).into(binding.pic)

        binding.plusCartItem.setOnClickListener {
            managementCart.plusNumberItem(items as ArrayList<PopularData>, position, object : ChangeNumberItemsListener {
                override fun change() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.change()
                }
            })
        }
        binding.minusCartItem.setOnClickListener {
            managementCart.minusNumberItem(items as ArrayList<PopularData>, position, object : ChangeNumberItemsListener {
                override fun change() {
                    notifyDataSetChanged()
                    changeNumberItemsListener.change()
                }
            })
        }


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(private val binding: CartlistItemBinding) : ViewHolder(binding.root) {

    }

}