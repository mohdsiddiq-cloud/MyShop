package com.siddiq.android.clothbazaar.helper

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "LoginPref"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"

    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun setLogin(context: Context, isLoggedIn: Boolean) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.apply()
    }
}