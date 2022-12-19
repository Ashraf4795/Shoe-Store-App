package com.example.android.shoestore.feature.shoe_list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.shoestore.base.PreferenceHelper
import com.example.android.shoestore.feature.login.data.LoginDataSource

class ShoeListViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeListViewModel::class.java)) {
            return ShoeListViewModel(shoeRepository = ShoeListRepository(ShoeDataSource, LoginDataSource.getInstance(
                PreferenceHelper.getInstance(context)
            ))) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}