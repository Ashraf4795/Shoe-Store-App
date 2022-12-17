package com.example.android.shoestore.feature.login.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.android.shoestore.R
import com.example.android.shoestore.databinding.FragmentLoginBinding
import com.example.android.shoestore.feature.login.data.model.User
import com.example.android.shoestore.feature.onboarding.OnBoardingActivity
import com.google.android.material.snackbar.Snackbar

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel =
            ViewModelProvider(this, LoginViewModelFactory())[LoginViewModel::class.java]

        val usernameEditText = binding.username
        val passwordEditText = binding.password
        val loginButton = binding.login
        val registerButton = binding.register
        val loadingProgressBar = binding.loading

        loginViewModel.loginFormState.observe(this.viewLifecycleOwner) { loginFormState ->
            if (loginFormState == null) {
                return@observe
            }
            loginButton.isEnabled = loginFormState.isDataValid
            loginFormState.usernameError?.let {
                usernameEditText.error = getString(it)
            }
            loginFormState.passwordError?.let {
                passwordEditText.error = getString(it)
            }
        }

        loginViewModel.loginResult.observe(this.viewLifecycleOwner) { loginResult ->
            loginResult ?: return@observe
            loadingProgressBar.visibility = View.GONE
            loginResult.error?.let {
                showLoginFailed(it)
            }
            loginResult.success?.let {
                updateUiWithUser(it)
            }
        }

        loginViewModel.registerResult.observe(this.viewLifecycleOwner) { registerResult ->
            registerResult ?: return@observe
            loadingProgressBar.visibility = View.GONE
            registerResult.success?.displayName?.let { registerMessage(it) }
        }

        val afterTextChangedListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                loginViewModel.loginDataChanged(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
        }

        usernameEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.addTextChangedListener(afterTextChangedListener)
        passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginViewModel.login(
                    usernameEditText.text.toString(),
                    passwordEditText.text.toString()
                )
            }
            false
        }

        loginButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.login(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        registerButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            loginViewModel.register(
                User(
                    userEmail = usernameEditText.text.toString(),
                    userPassword = passwordEditText.text.toString()
                )
            )
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome) + model.displayName
        Toast.makeText(this.requireActivity(), welcome, Toast.LENGTH_LONG).show()
        navController.navigate(R.id.action_loginFragment_to_shoeListFragment)
    }


    private fun registerMessage(message: String) {
        Toast.makeText(this.requireActivity(), "$message registered!", Toast.LENGTH_LONG).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(this.requireActivity(), errorString, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}