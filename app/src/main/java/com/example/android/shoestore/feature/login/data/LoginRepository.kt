package com.example.android.shoestore.feature.login.data

import com.example.android.shoestore.base.Result
import com.example.android.shoestore.feature.login.data.model.User

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

class LoginRepository(val dataSource: LoginDataSource) {

    val isLoggedIn: Boolean
        get() = dataSource.stubUser != null


    fun logout() {
        dataSource.logout()
    }

    suspend fun login(userEmail: String, password: String): Result<User> {
        val result = dataSource.login(User(userEmail = userEmail, userPassword = password))
        return result
    }

    suspend fun register(user: User): Result<User> {
        return dataSource.register(user)
    }
}