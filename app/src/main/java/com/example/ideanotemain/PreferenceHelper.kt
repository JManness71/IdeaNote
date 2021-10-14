package com.example.ideanotemain

import android.content.SharedPreferences

object PreferenceHelper {
    fun getString(prefs: SharedPreferences, key: String) : String? {
        val returnedItem: String? = prefs.getString(key, "Default")
        return returnedItem
    }

    fun setString(prefs: SharedPreferences, key: String, value: String) {
        val editor: SharedPreferences.Editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }
}