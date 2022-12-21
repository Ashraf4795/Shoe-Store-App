package com.example.android.shoestore.feature.shoe_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.shoestore.R
import com.example.android.shoestore.databinding.FragmentShoeDetailsBinding
import com.example.android.shoestore.feature.main.MainViewModel
import com.example.android.shoestore.feature.shoe_list.getRandomImage
import com.example.android.shoestore.feature.shoe_list.model.Shoe
import com.google.android.material.snackbar.Snackbar

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var navController: NavController
    private val mainViewModel: MainViewModel by activityViewModels()
    private val shoeDetailsObservable: ShoeDetailsObservable by lazy { ShoeDetailsObservable(onSave = {onSaveClicked(it)}, onCancel = {onCancelClicked()})}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentShoeDetailsBinding.inflate(inflater)
        binding.shoeDetails = shoeDetailsObservable

        navController = findNavController()

        return binding.root
    }

    private fun onSaveClicked(shoe: Shoe) {
        mainViewModel.shareShoeDetails(shoe)
        navController.popBackStack()
    }

    private fun onCancelClicked() {
        navController.popBackStack()
    }

    private fun showMessage(message: String) {
        val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_INDEFINITE).apply {
            setAction("OK") {
                dismiss()
            }
        }
        snackbar.show()
    }
}