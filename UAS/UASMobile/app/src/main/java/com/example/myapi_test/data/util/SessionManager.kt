package com.example.myapi_test.data.util

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "UserSession"
    private const val KEY_USER_EMAIL = "user_email"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveSession(context: Context, email: String) {
        val editor = getPreferences(context).edit()
        editor.putString(KEY_USER_EMAIL, email)
        editor.commit()
    }

    fun getCurrentUserEmail(context: Context): String? {
        return getPreferences(context).getString(KEY_USER_EMAIL, null)
    }

    fun clearSession(context: Context) {
        val editor = getPreferences(context).edit()
        editor.remove(KEY_USER_EMAIL)
        editor.commit()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getCurrentUserEmail(context) != null
    }
}
