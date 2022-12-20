package com.example.android.shoestore.feature.shoe_list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.shoestore.databinding.ShoeItemBinding
import com.example.android.shoestore.feature.shoe_details.DetailsState
import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeListAdapter(shoeList: List<Shoe>): RecyclerView.Adapter<ShoeListAdapter.ShoeViewHolder>() {

    private val mutableShoeList: MutableList<Shoe> = shoeList.toMutableList()

    class ShoeViewHolder(private val binding: ShoeItemBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(shoeItem: Shoe) {
            binding.shoeItem = shoeItem
            binding.shoeImage.setImageResource(shoeItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeViewHolder {
        val binding = ShoeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoeViewHolder, position: Int) {
        val shoeItem = mutableShoeList[position]
        holder.bind(shoeItem)
    }

    override fun getItemCount() = mutableShoeList.size

    fun updateShoeList(detailsState: DetailsState) {
        notifyDataSetChanged()
    }


}