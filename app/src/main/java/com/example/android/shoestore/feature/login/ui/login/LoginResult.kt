package com.example.android.shoestore.feature.login.ui.login

data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)