package com.siddiq.android.clothbazaar.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.siddiq.android.clothbazaar.activities.DetailActivity
import com.siddiq.android.clothbazaar.databinding.ProductlistItemBinding
import com.siddiq.android.clothbazaar.models.PopularData


class PopularDataAdapter(val items: List<PopularData>) : Adapter<PopularDataAdapter.MyViewHolder>() {
    lateinit var context: Context
    lateinit var binding : ProductlistItemBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context=parent.context
        var inflate=LayoutInflater.from(context)
        binding=ProductlistItemBinding.inflate(inflate,parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        binding.titleText.text=items.get(position).title
        binding.scoreText.text=items.get(position).score.toString()
        binding.priceText.text="$"+items.get(position).price
        val context=holder.itemView.context

        val drawableResourced=holder.itemView.resources
            .getIdentifier(items.get(position).picUrl,"drawable",holder.itemView.context.packageName)

        Glide.with(context).load(drawableResourced).transform(GranularRoundedCorners(30F,30F,0F,0F)).into(binding.pic)

        holder.itemView.setOnClickListener {
            val intent= Intent(context,DetailActivity::class.java)
            intent.putExtra("object",items.get(position))
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(private val binding: ProductlistItemBinding) : ViewHolder(binding.root) {

    }

}