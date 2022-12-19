package com.example.android.shoestore.feature.shoe_list

import com.example.android.shoestore.feature.login.data.LoginDataSource
import com.example.android.shoestore.feature.shoe_list.model.Shoe
import javax.inject.Inject
import javax.inject.Singleton

class ShoeListRepository (private val dataSource: ShoeDataSource, private val loginDataSource: LoginDataSource) {

    suspend fun getListOfShoes(): List<Shoe> {
        return dataSource.getListOfShoes()
    }

    fun addShoe(shoe: Shoe): List<Shoe> {
        return dataSource.addShoe(shoe)
    }

    fun isUserLoggedIn() = loginDataSource.isLoggedIn()

    fun logout() {
        loginDataSource.logout()
    }

}