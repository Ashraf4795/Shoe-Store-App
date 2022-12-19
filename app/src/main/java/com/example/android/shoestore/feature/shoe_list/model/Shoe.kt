package com.example.android.shoestore.feature.shoe_list.model

import com.example.android.shoestore.R
import com.example.android.shoestore.feature.shoe_details.DetailsState
import kotlin.random.Random

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
                Random(1000).nextInt(100, 900),
                R.drawable.shoe1,
                false
            )
        }
    }
}
