package com.example.android.shoestore.feature.shoe_list

import com.example.android.shoestore.R
import com.example.android.shoestore.feature.shoe_list.model.Shoe
import kotlinx.coroutines.delay
import javax.inject.Singleton

object ShoeDataSource {

    private val listOfShoes = mutableListOf<Shoe>().apply {
        add(Shoe("Nike", "Fly to the moon", 220, R.drawable.shoe1, false ))
        add(Shoe("Adidas", "Rock the field", 135, R.drawable.shoe2, false ))
        add(Shoe("Puma", "The fastest", 208, R.drawable.shoe3, false ))
        add(Shoe("Active", "Old is Gold", 80, R.drawable.shoe4, false ))
    }

    suspend fun getListOfShoes(): List<Shoe> {
        delay(1500)
        return listOfShoes
    }

    fun addShoe(shoe: Shoe): List<Shoe> {
        listOfShoes.add(shoe)
        return listOfShoes
    }
}
