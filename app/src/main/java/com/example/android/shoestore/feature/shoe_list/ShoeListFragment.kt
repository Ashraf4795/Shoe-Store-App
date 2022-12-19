package com.example.android.shoestore.feature.shoe_list

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.shoestore.R
import com.example.android.shoestore.base.PreferenceHelper
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.base.SHOE_APP_PREF
import com.example.android.shoestore.databinding.FragmentShoeListBinding
import com.example.android.shoestore.feature.login.data.LoginDataSource
import com.example.android.shoestore.feature.main.MainViewModel
import com.example.android.shoestore.feature.main.MainViewModelFactory
import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var shoeListAdapter: ShoeListAdapter
    private val mainViewModel: MainViewModel by activityViewModels<MainViewModel> {
        initMainViewModelFactory()
    }
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
        initObservers()
        initClickListener()
    }


    private fun initObservers() {
        mainViewModel.shoeLiveData.observe(this.viewLifecycleOwner) { result ->
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

        mainViewModel.detailsState.observe(this.viewLifecycleOwner) {
            shoeListAdapter.updateShoeList(it)
        }
    }

    private fun initClickListener() {
        binding.addShoeItemButtonId.setOnClickListener {
            navController.navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
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
            mainViewModel.logout()
            navController.popBackStack()
            navController.navigate(R.id.login_flow)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.drawer_menu, menu)
    }

    private fun initMainViewModelFactory() = MainViewModelFactory(
        ShoeListRepository(
            ShoeDataSource, LoginDataSource.getInstance(
                PreferenceHelper(this.requireActivity().applicationContext)
            )
        )
    )

}