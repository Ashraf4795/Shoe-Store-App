package com.example.android.shoestore.feature.shoe_list

import com.example.android.shoestore.R
import com.example.android.shoestore.feature.shoe_list.model.Shoe
import kotlinx.coroutines.delay
import javax.inject.Singleton

object ShoeDataSource {

    private val listOfShoes = mutableListOf<Shoe>()

    suspend fun getListOfShoes(): List<Shoe> {
        delay(1500)
        return listOfShoes
    }

    fun addShoe(shoe: Shoe): List<Shoe> {
        listOfShoes.add(shoe)
        return listOfShoes
    }
}
