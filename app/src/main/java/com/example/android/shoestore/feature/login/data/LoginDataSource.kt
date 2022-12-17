package com.example.android.shoestore.feature.login.data

import androidx.datastore.core.DataStore
import com.example.android.shoestore.base.Result
import com.example.android.shoestore.feature.login.data.model.User
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource() {

    fun login(username: String, password: String): Result<User> {
        try {
            val fakeUser = User(java.util.UUID.randomUUID().toString(), "Ashraf4747", "Ashraf.mohamed@gmail.com")

            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {

    }
}