package com.example.sigaps.utils

import android.content.Context
import android.content.SharedPreferences

class UserPreferences(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREF_NAME = "sigaps_prefs"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USERNAME = "username"
    }

    fun saveLoginInfo(username: String) {
        sharedPreferences.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USERNAME, username)
            apply()
        }
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(KEY_USERNAME, null)
    }

    fun logout() {
        sharedPreferences.edit().apply {
            clear()
            apply()
        }
    }
}