package com.example.android.shoestore.feature.shoe_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.shoestore.databinding.FragmentShoeDetailsBinding
import com.example.android.shoestore.feature.main.MainViewModel
import com.google.android.material.snackbar.Snackbar

class ShoeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var navController: NavController
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeDetailsBinding.inflate(inflater)
        navController = findNavController()

        initClickListeners()

        return binding.root
    }


    private fun initClickListeners() {
        binding.saveDetailsId.setOnClickListener {
            onSaveClicked()
        }

        binding.cancelDetailsChageId.setOnClickListener {
            onCancelClicked()
        }
    }

    private fun onSaveClicked() {
        val details = collectDetails()
        val detailsValidity = details.checkValues()

        if ( detailsValidity != DetailsState.Element.VALID) {
            showMessage(detailsValidity.message)
        } else {
            mainViewModel.shareShoeDetails(details)
            navController.popBackStack()
        }
    }

    private fun collectDetails(): DetailsState {
        return DetailsState(
            binding.shoeNameEditId.text.toString(),
            binding.shoeCompanyEditId.text.toString(),
            binding.shoeSizeEditId.text.toString(),
            binding.shoeDescriptionEditId.text.toString(),
        )
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