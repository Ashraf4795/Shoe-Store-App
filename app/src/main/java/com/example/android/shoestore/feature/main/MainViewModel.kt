package com.example.android.shoestore.feature.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.feature.shoe_details.DetailsState
import com.example.android.shoestore.feature.shoe_list.ShoeListRepository
import com.example.android.shoestore.feature.shoe_list.model.Shoe
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val shoeRepository: ShoeListRepository,
) : ViewModel() {

    private val _shoeMutableLiveData: MutableLiveData<Result<List<Shoe>>> = MutableLiveData()
    val shoeLiveData = _shoeMutableLiveData
    private val _detailsState: MutableLiveData<DetailsState> = MutableLiveData<DetailsState>()
    val detailsState: LiveData<DetailsState> = _detailsState

    init {
        viewModelScope.launch(dispatcher){

            _shoeMutableLiveData.postValue(Result.Loading("Loading Shoe List..."))
            val shoeList = shoeRepository.getListOfShoes()
            withContext(Dispatchers.Main) {
                _shoeMutableLiveData.value = Result.Success(data = shoeList)
            }
        }
    }

    fun isUserLoggedIn() = shoeRepository.isUserLoggedIn()

    fun logout() {
        shoeRepository.logout()
    }
    fun shareShoeDetails(details: DetailsState) {
        shoeRepository.addShoe(Shoe.createCopy(details))
        _detailsState.value = details
    }


}