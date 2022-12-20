package com.example.android.shoestore.feature.main

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import com.example.android.shoestore.R
import com.example.android.shoestore.base.PreferenceHelper
import com.example.android.shoestore.databinding.ActivityMainBinding
import com.example.android.shoestore.feature.login.data.LoginDataSource
import com.example.android.shoestore.feature.shoe_list.ShoeDataSource
import com.example.android.shoestore.feature.shoe_list.ShoeListRepository

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val mainViewModel: MainViewModel by viewModels {
        initMainViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_container) as NavHostFragment
        setContentView(binding.root)
        navController = navHostFragment.navController

        initViews()
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onStart() {
        super.onStart()
        checkLoginState()
    }

    private fun initViews() {
        drawerLayout = binding.drawerLayout
        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        navController.addOnDestinationChangedListener { nc, nd, bundle ->
            if (nc.graph.startDestinationId == nd.id) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
            } else {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
    }

    private fun checkLoginState() {
        if (!mainViewModel.isUserLoggedIn()) {
            navController.popBackStack()
            navController.navigate(R.id.login_flow)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(drawerLayout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    private fun initMainViewModelFactory() = MainViewModelFactory(
        ShoeListRepository(
            ShoeDataSource, LoginDataSource.getInstance(
                PreferenceHelper(this.applicationContext)
            )
        )
    )

}