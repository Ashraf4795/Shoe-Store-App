package com.example.android.shoestore.feature.shoe_list

import android.graphics.drawable.GradientDrawable


val gradients = listOf(
    GradientDrawable(
        GradientDrawable.Orientation.TL_BR,
        intArrayOf(0x42B1EF, 0x08A6FF)
    ),
    GradientDrawable(
        GradientDrawable.Orientation.TL_BR,
        intArrayOf(0xF7F98D, 0xECEF42)
    ),
    GradientDrawable(
        GradientDrawable.Orientation.TL_BR,
        intArrayOf(0xFEA8E6, 0xFC64D1)
    ),
    GradientDrawable(
        GradientDrawable.Orientation.TL_BR,
        intArrayOf(0x42B1EF, 0xFFCB12)
    ),
)


fun getRandomGradient() = gradients.random()