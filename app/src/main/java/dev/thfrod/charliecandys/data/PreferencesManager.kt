package dev.thfrod.charliecandys.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("userPref", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("login_token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("login_token", null)
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove("login_token")
        editor.apply()
    }
}
