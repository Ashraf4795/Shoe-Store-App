package com.example.android.shoestore.feature.login.data.model

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class User(
    val userId: String = java.util.UUID.randomUUID().toString(),
    val userEmail: String,
    val userPassword: String
) {
    fun isCredentialsCorrect(userEmail: String, userPassword: String): Boolean {
        return this.userEmail.equals(userEmail) && this.userPassword.equals(userPassword)
    }
}