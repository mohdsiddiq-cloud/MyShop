package com.siddiq.android.clothbazaar.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.siddiq.android.clothbazaar.activities.DetailActivity
import com.siddiq.android.clothbazaar.databinding.ProductlistItemBinding
import com.siddiq.android.clothbazaar.models.PopularData


class ItemAdapter(private val items: ArrayList<PopularData>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    lateinit var context: Context
    lateinit var binding : ProductlistItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context=parent.context
        var inflate=LayoutInflater.from(context)
        binding=ProductlistItemBinding.inflate(inflate,parent,false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        binding.titleText.text= items.get(position).title
        binding.priceText.text= "₹"+items.get(position).price


        val context=holder.itemView.context

//        val drawableResourced=holder.itemView.resources
//            .getIdentifier(items.get(position).picUrl,"mipmap",holder.itemView.context.packageName)
//
//        Glide.with(context).load(drawableResourced).transform(GranularRoundedCorners(30F,30F,0F,0F)).into(binding.pic)
//
        holder.itemView.setOnClickListener {
            val intent= Intent(context, DetailActivity::class.java)
            intent.putExtra("object",items.get(position))
            context.startActivity(intent)
        }
    }



    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private val binding: ProductlistItemBinding): RecyclerView.ViewHolder(binding.root) {

    }
}