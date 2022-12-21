package com.example.android.shoestore.feature.main

import androidx.databinding.Bindable
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


    private val _addingNewShoeState: MutableLiveData<Result<List<Shoe>>> = MutableLiveData<Result<List<Shoe>>>()
    val addingNewShoeState: LiveData<Result<List<Shoe>>> = _addingNewShoeState

    init {
        viewModelScope.launch(dispatcher){
            val shoeList = shoeRepository.getListOfShoes()
            _addingNewShoeState.postValue(Result.Loading("Loading..."))
            withContext(Dispatchers.Main) {
                _addingNewShoeState.value = Result.Success(data = shoeList)
            }
        }
    }

    fun isUserLoggedIn() = shoeRepository.isUserLoggedIn()

    fun logout() {
        shoeRepository.logout()
    }
    fun shareShoeDetails(shoe: Shoe) {
        _addingNewShoeState.value = Result.Success(shoeRepository.addShoe(shoe))
    }
    
}