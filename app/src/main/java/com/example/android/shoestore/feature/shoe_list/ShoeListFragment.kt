package com.example.android.shoestore.feature.shoe_list

import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.shoestore.R
import com.example.android.shoestore.base.PreferenceHelper
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.base.SHOE_APP_PREF
import com.example.android.shoestore.databinding.FragmentShoeListBinding
import com.example.android.shoestore.databinding.ShoeItemBinding
import com.example.android.shoestore.feature.login.data.LoginDataSource
import com.example.android.shoestore.feature.main.MainViewModel
import com.example.android.shoestore.feature.main.MainViewModelFactory
import com.example.android.shoestore.feature.shoe_list.model.Shoe

class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val mainViewModel: MainViewModel by activityViewModels {
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
        mainViewModel.addingNewShoeState.observe(this.viewLifecycleOwner) { result ->
            when(result) {
                is Result.Success -> {
                    renderShoeList(result.data)
                    binding.shoeListLoader.visibility = View.GONE
                }
                is Result.Loading -> {
                    binding.shoeListLoader.visibility = View.VISIBLE
                }
                is Result.Error -> {
                    showEmptyListView(true)
                    Toast.makeText(this.requireActivity(), "Failed", Toast.LENGTH_LONG).show()
                    binding.shoeListLoader.visibility = View.GONE
                }
            }
        }
    }

    private fun initClickListener() {
        binding.addShoeItemButtonId.setOnClickListener {
            navController.navigate(R.id.action_shoeListFragment_to_shoeDetailsFragment)
        }
    }

    private fun renderShoeList(shoes: List<Shoe>) {
        if (shoes.isEmpty()) {
            showEmptyListView(true)
        }
        for (shoe in shoes) {
            val shoeItemBinding = ShoeItemBinding.inflate(layoutInflater)
            shoeItemBinding.shoeItem = shoe
            binding.shoeItemsListId.addView(shoeItemBinding.root)
            showEmptyListView(false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_item) {
            mainViewModel.logout()
            navController.navigate(R.id.logout)
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

    fun showEmptyListView(isShow: Boolean) {
        binding.shoeItemsListId.visibility =  if (isShow) View.GONE else View.VISIBLE
        binding.emptyListImageId.visibility =  if (isShow) View.VISIBLE else View.GONE
    }

}