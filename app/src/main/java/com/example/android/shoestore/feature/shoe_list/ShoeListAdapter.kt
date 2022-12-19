package com.example.android.shoestore.feature.shoe_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoestore.databinding.ShoeItemBinding
import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeListAdapter(private val shoeList: List<Shoe>): RecyclerView.Adapter<ShoeListAdapter.ShoeViewHolder>() {


    class ShoeViewHolder(private val binding: ShoeItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(shoeItem: Shoe) {
            binding.shoeItemTitle.text = shoeItem.title
            binding.shoeItemPrice.text = shoeItem.price.toString().plus("$")
            binding.shoeItemDesc.text = shoeItem.description
            binding.shoeImage.setImageResource(shoeItem.image)
            binding.shoeItemContainer.background = getRandomGradient()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ShoeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoeItem = shoeList[position]
        holder.bind(shoeItem)
    }

    override fun getItemCount() = shoeList.size
}