package com.example.android.shoestore.feature.shoe_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.shoestore.R
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.databinding.FragmentShoeListBinding
import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var shoeListAdapter: ShoeListAdapter
    private lateinit var shoeListViewModel: ShoeListViewModel
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.inflate(layoutInflater)
        navController = findNavController()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoeListViewModel = ViewModelProvider(this, ShoeListViewModelFactory())[ShoeListViewModel::class.java]
        binding.headerId.back.setOnClickListener {
            navController.navigateUp()
        }
        initObservers()
    }

    private fun initObservers() {
        shoeListViewModel.shoeLiveData.observe(this.viewLifecycleOwner) { result ->
            when(result) {
                is Result.Success -> {
                    renderShoeList(result.data)
                    binding.shoeListLoader.visibility = View.GONE
                }
                is Result.Loading -> {
                    binding.shoeListLoader.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    Toast.makeText(this.requireActivity(), "Failed", Toast.LENGTH_LONG).show()
                    binding.shoeListLoader.visibility = View.GONE
                }
            }
        }
    }

    private fun renderShoeList(data: List<Shoe>) {
        shoeListAdapter = ShoeListAdapter(data)
        with(binding.shoeItemsListId) {
            adapter = shoeListAdapter
            layoutManager = GridLayoutManager(this@ShoeListFragment.requireActivity(), 2)
        }
    }
}