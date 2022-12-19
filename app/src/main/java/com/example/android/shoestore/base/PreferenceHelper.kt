package com.example.android.shoestore.base

import android.content.Context
import android.content.SharedPreferences
import com.example.android.shoestore.feature.login.data.model.User
import com.google.gson.Gson

class PreferenceHelper(val context: Context) {
    private val USER_LOGGED_FLAG = "LOGGED_FLAG"
    private val USER_OBJECT_FLAG = "USER_FLAG"
    private var preferences: SharedPreferences = context.getSharedPreferences(SHOE_APP_PREF, Context.MODE_PRIVATE)
    private val editor = preferences.edit()
    private val gson: Gson = Gson()


    companion object {
        @Volatile
        private lateinit var INSTANCE: PreferenceHelper
        fun getInstance(context: Context): PreferenceHelper {
            return if (this::INSTANCE.isInitialized) INSTANCE
            else {
                synchronized(this) {
                    if (!this::INSTANCE.isInitialized) {
                        INSTANCE = PreferenceHelper(context)
                    }
                }
                INSTANCE
            }
        }
    }

     fun cacheUser(user: User) {
        val userToJson = gson.toJson(user)
        editor.putString(USER_OBJECT_FLAG, userToJson).apply()
    }

    fun getCachedUser(): User? {
        val userJson = preferences.getString(USER_OBJECT_FLAG, null)
        val user = userJson?.let { gson.fromJson(userJson, User::class.java) }
        return user
    }

    fun isLoggedIn(default: Boolean): Boolean {
        return preferences.getBoolean(USER_LOGGED_FLAG, default)
    }

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        editor.putBoolean(USER_LOGGED_FLAG, isLoggedIn).apply()
    }


}