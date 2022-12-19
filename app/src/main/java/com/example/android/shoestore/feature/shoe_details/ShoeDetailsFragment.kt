package com.example.android.shoestore.feature.shoe_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.shoestore.R
import com.example.android.shoestore.databinding.FragmentShoeDetailsBinding
import com.example.android.shoestore.databinding.FragmentShoeListBinding

class ShoeDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailsBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeDetailsBinding.inflate(inflater)
        navController = findNavController()
        return binding.root
    }


}