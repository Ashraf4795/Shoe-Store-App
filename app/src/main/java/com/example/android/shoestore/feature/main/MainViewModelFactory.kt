package com.example.android.shoestore.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.shoestore.feature.shoe_list.ShoeListRepository
import java.lang.IllegalArgumentException

class MainViewModelFactory(private val shoeListRepository: ShoeListRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(shoeRepository = shoeListRepository) as T
        }
        throw IllegalArgumentException("$modelClass does not exists")
    }
}