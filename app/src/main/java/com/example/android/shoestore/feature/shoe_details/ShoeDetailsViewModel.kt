package com.example.android.shoestore.feature.shoe_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class ShoeDetailsViewModel {
    private val _detailsState: MutableLiveData<DetailsState> = MutableLiveData<DetailsState>()
    val detailsState: LiveData<DetailsState> = _detailsState

    fun checkDetailsValue(detailsState: DetailsState) {

    }
}