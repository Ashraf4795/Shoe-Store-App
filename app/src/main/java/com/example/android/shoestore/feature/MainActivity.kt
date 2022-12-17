package com.example.android.shoestore.feature

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.android.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomBar()
    }

    private fun setupBottomBar() {
        binding.bottomBar.onItemSelected = {
            Log.d("main", "$it selected")
        }

        binding.bottomBar.onItemReselected = {
            Log.d("main", "$it selected")
        }
    }
}