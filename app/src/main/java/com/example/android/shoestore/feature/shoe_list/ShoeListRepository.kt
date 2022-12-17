package com.example.android.shoestore.feature.shoe_list

import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeListRepository(private val dataSource: ShoeDataSource) {

    suspend fun getListOfShoes(): List<Shoe> {
        return dataSource.getListOfShoes()
    }

    fun addShoe(shoe: Shoe): List<Shoe> {
        return dataSource.addShoe(shoe)
    }

}