package com.example.android.shoestore.base

sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Loading(val loadingMsg: String): Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()
}