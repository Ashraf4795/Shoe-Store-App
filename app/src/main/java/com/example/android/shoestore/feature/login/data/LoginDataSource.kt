package com.example.android.shoestore.feature.login.data

import com.example.android.shoestore.R
import com.example.android.shoestore.base.PreferenceHelper
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.feature.login.data.model.User
import com.example.android.shoestore.feature.login.exception.LoginException
import com.example.android.shoestore.feature.login.exception.NoUserRegisteredException
import kotlinx.coroutines.delay

class LoginDataSource(private val preferenceHelper: PreferenceHelper) {

    var stubUser: User? = null
        private set

    companion object {
        @Volatile
        private lateinit var INSTANCE: LoginDataSource
        fun getInstance(preferenceHelper: PreferenceHelper): LoginDataSource {
            return if (this::INSTANCE.isInitialized) INSTANCE
            else {
                synchronized(this) {
                    if (!this::INSTANCE.isInitialized) {
                        INSTANCE = LoginDataSource(preferenceHelper)
                    }
                }
                return INSTANCE
            }
        }
    }

    suspend fun login(user: User): Result<User> {
        delay(1500)
        try {
            //if (checkUserInMemory(user)) return Result.Success(stubUser!!)

            val cachedUser = preferenceHelper.getCachedUser()

            if (cachedUser == null) {
                return Result.Error(
                    R.string.registration_required,
                    NoUserRegisteredException("You Need to register first!")
                )
            }

            if (cachedUser.isCredentialsCorrect(user.userEmail, user.userPassword)) {
                preferenceHelper.setUserLoggedIn(true)
                return Result.Success(cachedUser)
            } else
                return Result.Error(
                    R.string.login_failed,
                    LoginException("user email or password is not correct")
                )
        } catch (e: Throwable) {
            return Result.Error(R.string.login_failed, LoginException("Error logging in"))
        }
    }

    private fun checkUserInMemory(user: User): Boolean {
        if (stubUser != null) {
            return true
        }
        return false
    }

    suspend fun register(user: User): Result<User> {
        stubUser = user
        preferenceHelper.cacheUser(user)
        delay(1500)
        return Result.Success(user)
    }

    fun logout() {
        preferenceHelper.setUserLoggedIn(false)
        stubUser = null
    }

    fun isLoggedIn() = preferenceHelper.isLoggedIn(false)
}