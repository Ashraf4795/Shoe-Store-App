package com.example.android.shoestore.feature.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.shoestore.R
import com.example.android.shoestore.databinding.FragmentInstructionBinding
import com.example.android.shoestore.databinding.FragmentOnBoardingBinding

class InstructionFragment : Fragment() {
    private lateinit var binding: FragmentInstructionBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstructionBinding.inflate(inflater)
        navController = findNavController()

        binding.instructionNextButtonId.setOnClickListener {
            navController.navigate(R.id.action_global_shoeListFragment)
        }

        return binding.root
    }

}