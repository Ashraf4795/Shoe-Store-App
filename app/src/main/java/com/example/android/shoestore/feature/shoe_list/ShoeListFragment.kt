package com.example.android.shoestore.feature.shoe_list

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.shoestore.R
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.base.SHOE_APP_PREF
import com.example.android.shoestore.databinding.FragmentShoeListBinding
import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var shoeListAdapter: ShoeListAdapter
    private lateinit var shoeListViewModel: ShoeListViewModel
    private lateinit var navController: NavController
    private lateinit var preference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = this.requireActivity().getSharedPreferences(SHOE_APP_PREF, AppCompatActivity.MODE_PRIVATE)

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
        setHasOptionsMenu(true)
        shoeListViewModel = ViewModelProvider(this, ShoeListViewModelFactory(requireActivity().applicationContext))[ShoeListViewModel::class.java]
        initObservers()
        checkLoginState()
    }

    private fun checkLoginState() {
        if (!shoeListViewModel.isUserLoggedIn()) {
            navController.navigate(R.id.action_shoeListFragment_to_login_flow)
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_item) {
            shoeListViewModel.logout()
            navController.navigate(R.id.action_shoeListFragment_to_login_flow)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drawer_menu, menu)
    }
}