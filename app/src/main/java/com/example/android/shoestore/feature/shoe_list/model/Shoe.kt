package com.example.android.shoestore.feature.shoe_list.model

import com.example.android.shoestore.R
import com.example.android.shoestore.feature.shoe_details.DetailsState

data class Shoe(
    val title: String,
    val company:String,
    val description: String,
    val size: String,
    val price: Int,
    val image: Int,
    val isFav: Boolean,
) {
    companion object {
        fun createCopy(detailsState: DetailsState): Shoe {
            return Shoe(
                detailsState.name,
                detailsState.company,
                detailsState.description,
                detailsState.size,
                (100..1000).random(),
                R.drawable.shoe1,
                false
            )
        }
    }
}
