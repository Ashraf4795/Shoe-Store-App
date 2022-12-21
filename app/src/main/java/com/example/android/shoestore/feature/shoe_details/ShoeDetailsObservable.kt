package com.example.android.shoestore.feature.shoe_details

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.example.android.shoestore.BR
import com.example.android.shoestore.feature.shoe_list.getRandomImage
import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeDetailsObservable(
    private val onSave: (Shoe) -> Unit,
    private val onCancel: () -> Unit
) : BaseObservable() {

    @get:Bindable
    var shoeName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoeName)
        }

    @get:Bindable
    var shoeCompany: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoeCompany)
        }

    @get:Bindable
    var shoeSize: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoeSize)
        }

    @get:Bindable
    var shoeDescription: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.shoeDescription)
        }

    fun onSaveButtonClicked() {
        return onSave.invoke(constructShoeItem(shoeName, shoeCompany, shoeSize, shoeDescription))
    }

    fun onCancelButtonClicked() {
        onCancel.invoke()
    }

    fun constructShoeItem(name: String, companyName: String, size: String, desc: String): Shoe {
        return Shoe(
            name, companyName, desc, size, (100..1000).random(),
            getRandomImage(),
            false
        )
    }
}