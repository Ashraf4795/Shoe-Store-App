package com.example.android.shoestore.feature.shoe_list

import com.example.android.shoestore.R
import kotlin.random.Random
import kotlin.random.nextInt

val shoeList = listOf(
    R.drawable.shoe1,
    R.drawable.shoe2,
    R.drawable.shoe3,
    R.drawable.shoe4,
)

fun getRandomImage() = shoeList.random()