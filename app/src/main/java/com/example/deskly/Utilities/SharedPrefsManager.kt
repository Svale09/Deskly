package com.example.deskly.Utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPrefsManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object{
        private const val PREFS_NAME = "my_prefs"
        private const val USER_ROLE_KEY = "user_role"

        @Volatile
        private var INSTANCE: SharedPrefsManager? = null

        fun getInstance(context: Context): SharedPrefsManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SharedPrefsManager(context).also { INSTANCE = it }
            }
        }
    }

    fun saveUserRole(role: Int){
        sharedPreferences.edit().putInt(USER_ROLE_KEY, role).apply()
    }

    fun getUserRole(): Int{
        return sharedPreferences.getInt(USER_ROLE_KEY, 0)
    }

}