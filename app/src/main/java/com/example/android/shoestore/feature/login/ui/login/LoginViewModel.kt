package com.example.android.shoestore.feature.login.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.shoestore.R
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.feature.login.data.LoginRepository
import com.example.android.shoestore.feature.login.data.model.User
import kotlinx.coroutines.*

class LoginViewModel(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _registerResult = MutableLiveData<LoginResult>()
    val registerResult: LiveData<LoginResult> = _registerResult

    fun login(username: String, password: String) {
        viewModelScope {
            when (val result = loginRepository.login(username, password)) {
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        _loginResult.value =
                            LoginResult(success = LoggedInUserView(displayName = result.data.userEmail))
                    }
                }
                is Result.Error -> {
                    withContext(Dispatchers.Main) {
                        _loginResult.value = LoginResult(error = result.errorId)
                    }
                }
                else -> {}
            }
        }
    }

    fun register(user: User) {
        viewModelScope {
            when (val registrationResult = loginRepository.register(user)) {
                is Result.Success -> {
                    withContext(Dispatchers.Main) {
                        _registerResult.value =
                            LoginResult(success = LoggedInUserView(displayName = registrationResult.data.userEmail))
                    }
                }
                else -> {}
            }
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private fun viewModelScope(
        dispatcher: CoroutineDispatcher = this.dispatcher,
        block: suspend () -> Unit
    ) {
        viewModelScope.launch {
            withContext(dispatcher) {
                block.invoke()
            }
        }
    }

    fun isUserLoggedIn() {

    }
}