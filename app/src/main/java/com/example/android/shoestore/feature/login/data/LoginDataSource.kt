package com.example.android.shoestore.feature.login.data

import com.example.android.shoestore.R
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.feature.login.data.model.User
import com.example.android.shoestore.feature.login.exception.LoginException
import com.example.android.shoestore.feature.login.exception.NoUserRegisteredException
import kotlinx.coroutines.delay

class LoginDataSource() {
    // user is not logged in by default when app first launch.
    private var stubUser: User? = null

   suspend fun login(user: User): Result<User> {
       delay(1500)
        try {
            if (stubUser == null) {
                return Result.Error(R.string.registration_required, NoUserRegisteredException("You Need to register first!"))
            }
            if (stubUser?.isCredentialsCorrect(user.userEmail, user.userPassword) == true)
                return Result.Success(stubUser!!)
            else
                return Result.Error(R.string.login_failed, LoginException("user email or password is not correct"))
        } catch (e: Throwable) {
            return Result.Error(R.string.login_failed, LoginException( "Error logging in"))
        }
    }

    suspend fun register(user: User): Result<User> {
        stubUser = user
        delay(1500)
        return Result.Success(user)
    }
    fun logout() {
        stubUser = null
    }
}