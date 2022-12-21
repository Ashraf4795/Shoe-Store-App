package com.example.android.shoestore.feature.shoe_details

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable

data class DetailsState(
    @Bindable()
    val name: String,
    val company: String,
    val size: String,
    val description: String,
): BaseObservable() {
    enum class Element(val message: String){
        NAME("Name not valid, should be more than 3 letters"),
        COMPANY("Company not valid, enter company name."),
        SIZE("Size not valid, enter shoe size number"),
        DESC("Description not valid, enter shoe description"),
        VALID("Details are valid")
    }
    fun checkValues(): Element {
        return if (name.isBlank() || name.length < 3) return Element.NAME
        else if (company.isBlank()) return Element.COMPANY
        else if (size.isBlank()) return Element.SIZE
        else if (description.isBlank()) return Element.DESC
        else Element.VALID
    }
}


