package com.example.android.shoestore.feature.shoe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoeListViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeListViewModel::class.java)) {
            return ShoeListViewModel(repository = ShoeListRepository(ShoeDataSource())) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}