package com.example.android.shoestore.feature.login.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.shoestore.base.PreferenceHelper
import com.example.android.shoestore.feature.login.data.LoginDataSource
import com.example.android.shoestore.feature.login.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource(PreferenceHelper.getInstance(context))
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}